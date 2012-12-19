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

/**
 * Converts the Json from the body of the request to the Java bean with the
 * class specified in the {@code Bind-Class} request header using
 * {@linkplain ObjectMapper}.
 * 
 * @author gushakov
 * 
 */
public class WidgetBodyHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {
	private static final Logger logger = LoggerFactory
			.getLogger(WidgetBodyHandlerMethodArgumentResolver.class);

	private ObjectMapper jacksonMapper;

	public void setJacksonMapper(ObjectMapper jacksonMapper) {
		this.jacksonMapper = jacksonMapper;
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

		String bindClassHeader = webRequest.getHeader("Bind-Class");
		if (bindClassHeader == null) {
			throw new IllegalArgumentException(
					"Request does not have Bind-Class header.");
		}

		// unmarshall request body (Json) to an widget instance
		return jacksonMapper.readValue(servletRequest.getReader(),
				Class.forName(bindClassHeader));
	}

}
