package com.github.dojotags.web.config;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.dojotags.web.annotation.WidgetBody;
import com.github.dojotags.widgets.Button;
import com.github.dojotags.widgets.Input;
import com.github.dojotags.widgets.Label;

public class WidgetBodyHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {
	private static final Logger logger = LoggerFactory
			.getLogger(WidgetBodyHandlerMethodArgumentResolver.class);

	private ObjectMapper jacksonMapper;

	public WidgetBodyHandlerMethodArgumentResolver() {
		jacksonMapper = new ObjectMapper();
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(WidgetBody.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {

		logger.debug(
				"Resolving WidgetBody argument with generic parameter type {}",
				parameter.getGenericParameterType());

		// check that this is Json type request
		if (!webRequest.getHeader("Content-Type").contains("application/json")) {
			throw new IllegalArgumentException(
					"Request content type is not application/json.");
		}

		// check if this web request is a HttpServletRequest
		HttpServletRequest servletRequest = webRequest
				.getNativeRequest(HttpServletRequest.class);
		if (servletRequest == null) {
			throw new IllegalArgumentException("Request "
					+ webRequest.getClass().getName()
					+ " is not instance of HttpServletRequest.");
		}

		String widgetClassHeader = webRequest.getHeader("Widget-Class");
		if (widgetClassHeader == null) {
			throw new IllegalArgumentException(
					"Request does not have Widget-Class header.");
		}

		// determine the widget type corresponding to the value of the
		// request header
		Class<?> widgetType = null;
		if (widgetClassHeader.equals("dojotags.Label")) {
			widgetType = Label.class;
		} else if (widgetClassHeader.equals("dojotags.Button")) {
			widgetType = Button.class;
		} else if (widgetClassHeader.equals("dojotags.Input")) {
			widgetType = Input.class;
		} else if (widgetClassHeader.equals("dojotags.Form")) {
			String formClassHeader = webRequest.getHeader("Form-Class");
			if (formClassHeader == null) {
				throw new IllegalArgumentException(
						"Request does not have Form-Class header.");
			}
			widgetType = Class.forName(formClassHeader);
		} else {
			throw new IllegalArgumentException(
					"Cannot resolve web argument for the Widget-Class header value "
							+ widgetClassHeader + ".");
		}

		// unmarshall request body (Json) to an widget instance
		return jacksonMapper.readValue(servletRequest.getReader(), widgetType);
	}

}
