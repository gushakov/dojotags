package com.github.dojotags.web.config;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.dojotags.model.Button;
import com.github.dojotags.model.Input;
import com.github.dojotags.model.Label;
import com.github.dojotags.model.Widget;
import com.github.dojotags.web.annotation.WidgetBody;

public class WidgetBodyHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

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
		Class<? extends Widget> widgetType = null;
		if (widgetClassHeader.equals("dojotags.Label")) {
			widgetType = Label.class;
		} else if (widgetClassHeader.equals("dojotags.Button")) {
			widgetType = Button.class;
		} 
		else if(widgetClassHeader.equals("dojotags.Input")){
			widgetType = Input.class;
		}
		else {
			widgetType = Widget.class;
		}

		// unmarshall request body (Json) to an widget instance
		return jacksonMapper.readValue(servletRequest.getReader(), widgetType);
	}

}
