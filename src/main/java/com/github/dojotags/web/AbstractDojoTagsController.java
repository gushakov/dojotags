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

import com.github.dojotags.json.Response;
import com.github.dojotags.web.annotation.WidgetBody;
import com.github.dojotags.web.annotation.WidgetEventMapping;

@RequestMapping(value = "/dojotags", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
public abstract class AbstractDojoTagsController {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractDojoTagsController.class);

	@RequestMapping(value = "/widget/{widgetId}/event/{event}")
	public @ResponseBody
	Response processWidgetEvent(@PathVariable("widgetId") String widgetId,
			@PathVariable("event") String event, @WidgetBody Object widget) {
		logger.debug(
				"Processing dojotags Ajax request with widget id {}, event {} and widget model {}",
				new Object[] { widgetId, event, widget });

		Response data = null;

		// find an appropriate handler method by reading @DojoTag
		// method annotations for matching value (widget id)

		Method[] methods = getClass().getDeclaredMethods();
		for (Method method : methods) {
			Annotation annot = AnnotationUtils.findAnnotation(method,
					WidgetEventMapping.class);
			if (annot == null) {
				continue;
			}

			String annotWidgetId = (String) AnnotationUtils.getValue(annot,
					"widgetId");
			String annotEvent = (String) AnnotationUtils.getValue(annot,
					"event");
			if (!annotWidgetId.equals(widgetId) || !annotEvent.equals(event)) {
				continue;
			}

			logger.debug(
					"Found handler method {} for widget {} and event {}",
					new Object[] { method.getName(), annotWidgetId, annotEvent });

			// execute the handler method
			try {
				data = (Response) method.invoke(this, widget);
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
