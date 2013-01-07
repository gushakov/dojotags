package com.github.dojotags.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.dojotags.web.annotation.ViewModel;
import com.github.dojotags.web.annotation.WidgetEvent;

/**
 * Controller for handling widget events. Will handle all POST requests to
 * <code>/dojotags/widget/{widgetId}/event/{event}</code> URL with Json body.
 * 
 * @author gushakov
 * 
 */
public abstract class AbstractDojoTagsController {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractDojoTagsController.class);

	@RequestMapping(value = "/dojotags/widget/{widgetId}/event/{event}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody
	Object processWidgetEvent(@PathVariable("widgetId") String widgetId,
			@PathVariable("event") String event, @ViewModel Object viewModel) {
		logger.debug(
				"Processing dojotags Ajax request with widget id {}, event {} and view model {}",
				new Object[] { widgetId, event, viewModel });

		Object data = null;

		// find an appropriate handler method by reading @DojoTag
		// method annotations for matching value (widget id)

		Method[] methods = viewModel.getClass().getMethods();
		for (Method method : methods) {
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
				break;
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return data;
	}

}
