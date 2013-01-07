package com.github.dojotags.web.config;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.dojotags.web.annotation.ViewModel;
import com.github.dojotags.widgets.Widget;

/**
 * Uses {@linkplain ObjectMapper} to convert the body of the request to a
 * {@code Widget} and sets this widget in the view-model retrieved from the
 * application context by the class name specified in the "View-Class" header.
 * 
 * @author gushakov
 * 
 */
public class ViewModelHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {
	private static final Logger logger = LoggerFactory
			.getLogger(ViewModelHandlerMethodArgumentResolver.class);

	private ObjectMapper jacksonMapper;

	public void setJacksonMapper(ObjectMapper jacksonMapper) {
		this.jacksonMapper = jacksonMapper;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(ViewModel.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {

		Object viewModel = null;

		logger.debug(
				"Resolving ViewModel argument with generic parameter type {}",
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

		String viewClassHeader = webRequest.getHeader("View-Class");
		if (viewClassHeader == null) {
			throw new IllegalArgumentException(
					"Request does not have View-Class header.");
		}

		String bindClassHeader = webRequest.getHeader("Bind-Class");
		if (bindClassHeader == null) {
			throw new IllegalArgumentException(
					"Request does not have Bind-Class header.");
		}

		Class<?> widgetClass = Class.forName(bindClassHeader);

		// unmarshall request body (Json) to a widget instance
		Widget widget = (Widget) jacksonMapper.readValue(
				servletRequest.getReader(), widgetClass);
		logger.debug("Unmarshalled widget with id {} from request body",
				widget.getId());

		// get the application context
		WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(webRequest
						.getNativeRequest(HttpServletRequest.class)
						.getSession().getServletContext());
		logger.debug("Got web application context {}", context);

		// get the view-model bean from the application context
		viewModel = context.getBean(Class.forName(viewClassHeader));
		logger.debug(
				"Got view-model bean from application context {} with class {}",
				viewModel, viewModel.getClass());

		// invoke setter for the field in the view-model corresponding to the
		// widget's name, note we cannot directly set the field value since the
		// actual view-model object we get is a session-scoped proxy
		try {
			Method method = viewModel.getClass().getMethod(
					getSetter(widget.getName()), widgetClass);
			method.invoke(viewModel, widget);
		} catch (NoSuchMethodException e) {
			// ignore, we are not interested in listening to this widget's
			// change events
		}

		return viewModel;
	}

	private String getSetter(String fieldName) {
		return "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
	}

}
