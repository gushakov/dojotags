package com.github.dojotags.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.dojotags.utils.Utils;
import com.github.dojotags.web.annotation.ViewModel;
import com.github.dojotags.web.annotation.WidgetEvent;

/**
 * Controller for handling widget events. Will handle all POST requests with
 * content type "application/json" submitted to URL matching
 * <code>/dojotags/widget/{widgetId}/event/{event}</code> pattern. Resolves
 * {@code viewModel} object by looking up a session bean from the application
 * context using the value of "View-Class" header attribute and updates it with
 * the relevant widget unmarshalled from the request body. Calls any method of
 * {@code viewModel} annotated with {@code WidgetEvent} with matching event
 * name.
 * 
 * @author gushakov
 * 
 */
public abstract class AbstractDojoTagsController {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractDojoTagsController.class);

	@RequestMapping(value = "/dojotags/widget/{widgetId}/event/{event}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody
	Map<String, Object> processWidgetEvent(@PathVariable("widgetId") String widgetId,
			@PathVariable("event") String event, @ViewModel Object viewModel) {
		logger.debug(
				"Processing dojotags Ajax request with widget id {}, event {} and view model {}",
				new Object[] { widgetId, event, viewModel });

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
				logger.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				logger.error(e.getMessage(), e);
			}

			// add the updates to the response
			String[] annotUpdates = (String[]) AnnotationUtils.getValue(annot, "updates");
				List<Map<String, Object>> updates = new ArrayList<Map<String,Object>>();
				Map<String, Object> update = new HashMap<String, Object>();
				for (String name : annotUpdates) {
					Method getter = Utils.findMehod(Utils.getGetter(name), viewModel.getClass().getMethods());
					try {
						update.put(name, getter.invoke(viewModel));
						updates.add(update);
					} catch (IllegalArgumentException e) {
						logger.error(e.getMessage(), e);
					} catch (IllegalAccessException e) {
						logger.error(e.getMessage(), e);
					} catch (InvocationTargetException e) {
						logger.error(e.getMessage(), e);
					}
				}
				response.put("updates", updates);
			break;
		}

		return response;
	}

}
