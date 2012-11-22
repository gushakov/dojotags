package com.github.dojotags.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.dojotags.web.annotation.DojoTag;

@RequestMapping(value = "/dojotags", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
public abstract class AbstractDojoTagsController {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractDojoTagsController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/widget/{widgetId}/event/{event}")
	public @ResponseBody
	Map<String, Object> processWidgetEvent(
			@PathVariable("widgetId") String widgetId,
			@PathVariable("event") String event,
			@RequestBody Map<String, Object> model) {
		logger.debug(
				"Processing dojotags Ajax request with widgetId {},  event {}, and model {}.",
				new Object[] { widgetId, event, model });

		Map<String, Object> data = null;

		// find an appropriate handler method by reading @DojoTag
		// method annotations for matching value (widget id)

		Method[] methods = getClass().getDeclaredMethods();
		for (Method method : methods) {
			Annotation annot = AnnotationUtils.findAnnotation(method,
					DojoTag.class);
			if (annot == null) {
				continue;
			}

			String annotWidgetId = (String) AnnotationUtils.getValue(annot);
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
				data = (Map<String, Object>) method.invoke(this, model);
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
