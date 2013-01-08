package com.github.dojotags.web;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.dojotags.utils.Utils;
import com.github.dojotags.web.annotation.WidgetEvent;

/**
 * Controller for handling widget events. Will handle all POST requests with
 * content type "application/json" submitted to URL matching
 * <code>/dojotags/widget/{widgetId}/event/{event}</code> pattern.
 * 
 * @author gushakov
 * 
 */
public abstract class AbstractDojoTagsController {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractDojoTagsController.class);

	private ObjectMapper jacksonMapper;

	public AbstractDojoTagsController() {
		jacksonMapper = new ObjectMapper();
	}

	/**
	 * Looks up a view-model bean from the application context using the value
	 * of "View-Class" request header. Deserializes Json from the body of the
	 * request and converts it to the map representing the state of the widget
	 * which initiated the event. Sets the view-model property bound to the
	 * widget using "name" and "value" properties of the map. Executes first
	 * found event handling method of the view-model annotated with
	 * {@code WidgetEvent} specifying the same event name. Returns the list of
	 * updates for each view-model property specified by the annotation each
	 * containing the updated value to be set in the view.
	 * 
	 * @param widgetId
	 *            id of the widget which initiated the event
	 * @param event
	 *            event name as specified in the view
	 * @param request
	 *            current request
	 * @return response to be processed by the view
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/dojotags/widget/{widgetId}/event/{event}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody
	Map<String, Object> processWidgetEvent(
			@PathVariable("widgetId") String widgetId,
			@PathVariable("event") String event, HttpServletRequest request) {
		logger.debug(
				"Processing dojotags Ajax request for widget {} and event {}",
				new Object[] { widgetId, event });
		Object viewModel = null;

		String viewClassHeader = request.getHeader("View-Class");
		if (viewClassHeader == null) {
			throw new RuntimeException(
					"Request does not have View-Class header.");
		}

		String widgetTypeHeader = request.getHeader("Widget-Type");
		if (widgetTypeHeader == null) {
			throw new RuntimeException(
					"Request does not have Widget-Type header.");
		}

		// unmarshall request body Json to a map of widget model attributes
		Map<String, Object> map = null;
		try {
			map = (Map<String, Object>) jacksonMapper.readValue(
					request.getReader(), Map.class);
		} catch (JsonParseException e) {
			throw new RuntimeException("Cannot deserialize widget state.", e);
		} catch (JsonMappingException e) {
			throw new RuntimeException("Cannot deserialize widget state.", e);
		} catch (IOException e) {
			throw new RuntimeException("Cannot deserialize widget state.", e);
		}
		logger.debug(
				"Unmarshalled widget state change request for widget with id {}",
				map.get("id"));

		// get the application context
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		logger.debug("Got web application context {}", context);

		// get the view-model bean from the application context
		try {
			viewModel = context.getBean(Class.forName(viewClassHeader));
		} catch (BeansException e) {
			throw new RuntimeException(
					"Error when looking up a view-model bean with type "
							+ viewClassHeader + " in the appilcation context.",
					e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(
					"Error when looking up a view-model bean with type "
							+ viewClassHeader + " in the appilcation context.",
					e);
		}
		logger.debug(
				"Got view-model bean from application context {} with class {}",
				viewModel, viewModel.getClass());

		// invoke setter for the field in the view-model corresponding to the
		// widget's name, note we cannot directly set the field value since the
		// actual view-model object we get is a session-scoped proxy
		Method setter = Utils.findMehod(Utils.getSetter((String) map
				.get("name")), viewModel.getClass().getMethods());
		if (setter != null) {
			try {
				setter.invoke(
						viewModel,
						convertValue(widgetTypeHeader, map.get("value"),
								setter.getParameterTypes()[0]));
			} catch (IllegalArgumentException e) {
				throw new RuntimeException("Cannot execute setter method "
						+ setter.getName() + " on the view-model bean.", e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Cannot execute setter method "
						+ setter.getName() + " on the view-model bean.", e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException("Cannot execute setter method "
						+ setter.getName() + " on the view-model bean.", e);
			}
		}

		Map<String, Object> response = new HashMap<String, Object>();

		// find an appropriate handler method by reading @DojoTag
		// method annotations for matching value (widget id)

		for (Method method : viewModel.getClass().getMethods()) {
			Annotation annot = AnnotationUtils.findAnnotation(method,
					WidgetEvent.class);
			if (annot == null) {
				continue;
			}

			String annotEvent = (String) AnnotationUtils.getValue(annot);
			if (!annotEvent.equals(event)) {
				continue;
			}

			logger.debug("Found handler method {} for event {}", new Object[] {
					method.getName(), annotEvent });

			// execute the handler method
			try {
				method.invoke(viewModel);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(
						"Cannot execute event handler method "
								+ method.getName() + " on the view-model bean",
						e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(
						"Cannot execute event handler method "
								+ method.getName() + " on the view-model bean",
						e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(
						"Cannot execute event handler method "
								+ method.getName() + " on the view-model bean",
						e);
			}

			// add the updates to the response
			String[] annotUpdates = (String[]) AnnotationUtils.getValue(annot,
					"updates");
			List<Map<String, Object>> updates = new ArrayList<Map<String, Object>>();
			Map<String, Object> update = new HashMap<String, Object>();
			for (String name : annotUpdates) {
				Method getter = Utils.findMehod(Utils.getGetter(name),
						viewModel.getClass().getMethods());
				try {
					update.put(name, getter.invoke(viewModel));
					updates.add(update);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("Cannot execute getter method "
							+ getter.getName() + " on the view-model bean.", e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Cannot execute getter method "
							+ getter.getName() + " on the view-model bean.", e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException("Cannot execute getter method "
							+ getter.getName() + " on the view-model bean.", e);
				}
			}
			response.put("updates", updates);
			break;
		}

		return response;
	}

	/**
	 * Converts the value from the deserialized widget state map to the value
	 * assignable to the view-model property.
	 * 
	 * @param widgetType
	 *            widget type
	 * @param fromValue
	 *            deserialized value
	 * @param valueType
	 *            type of the value expected by the view-model
	 * @return assignable to the view-model property
	 */
	protected <T> T convertValue(String widgetType, Object fromValue,
			Class<T> valueType) {
		T toValue = null;
		if (widgetType.equals("dojotags.Input")) {
			toValue = valueType.cast(fromValue);
		} else {
			throw new RuntimeException("Unknown wiget type " + widgetType);
		}
		return toValue;
	}

}
