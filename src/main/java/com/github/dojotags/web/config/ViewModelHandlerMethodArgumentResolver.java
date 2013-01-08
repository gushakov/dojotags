package com.github.dojotags.web.config;

import java.lang.reflect.Method;
import java.util.Map;

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

import com.github.dojotags.utils.Utils;
import com.github.dojotags.web.annotation.ViewModel;

/**
 * Resolver for the web arguments annotated with {@code ViewModel}. Uses
 * {@linkplain ObjectMapper} to convert the body of the request to a
 * {@code Map<String, Object>} map corresponding to the set of widget model
 * attributes representing the change in the widget's state. Looks up a
 * view-model bean (session scoped) in the application context using the value
 * of "View-Class" request header and sets the appropriate member variable using
 * "name" and "value" attributes of the map.
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

		String widgetTypeHeader = webRequest.getHeader("Widget-Type");
		if (widgetTypeHeader == null) {
			throw new IllegalArgumentException(
					"Request does not have Bind-Class header.");
		}

		// unmarshall request body (Json) to a widget instance
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) jacksonMapper
				.readValue(servletRequest.getReader(), Map.class);
		logger.debug(
				"Unmarshalled widget state change request for widget with id {}",
				map.get("id"));

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
		Method method = Utils.findMehod(Utils.getSetter((String) map.get("name")),
				viewModel.getClass().getMethods());
		if (method != null) {
			method.invoke(
					viewModel,
					convertValue(widgetTypeHeader,
							map.get("value"), method.getParameterTypes()[0]));
		}

		return viewModel;
	}

	private <T> T convertValue(String widgetType, Object fromValue,
			Class<T> valueType) {
		T toValue = null;
		if (widgetType.equals("dojotags.Input")) {
			toValue = valueType.cast(fromValue);
		} else {
			throw new IllegalArgumentException("Unknown wiget type "
					+ widgetType);
		}
		return toValue;
	}

}
