package com.github.dojotags.web.config;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.github.dojotags.web.annotation.WidgetBinding;
import com.github.dojotags.web.registry.WidgetsRegistry;
import com.github.dojotags.widgets.Widget;

public class WidgetsBindingHandlerInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory
			.getLogger(WidgetsBindingHandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		logger.debug("Intercepting handler {}", handlerMethod.getBeanType());

		WidgetsRegistry widgetsRegistry = WebApplicationContextUtils
				.getRequiredWebApplicationContext(
						request.getSession().getServletContext()).getBean(
						WidgetsRegistry.class);

		logger.debug("WidgetRegistry bean {}", widgetsRegistry);

		Field[] fields = handlerMethod.getBeanType().getDeclaredFields();
		for (Field field : fields) {
			logger.debug("Field: {}", field.getName());
			WidgetBinding annot = AnnotationUtils.getAnnotation(field,
					WidgetBinding.class);
			if (annot != null) {
				String id = annot.id();
				Widget widget = widgetsRegistry.get(annot.id(), Widget.class);
				if (widget == null) {
					logger.debug("Creating a new widget with id {}", id);
					widget = (Widget) field.getType().newInstance();
					widget.setId(id);
					widgetsRegistry.put(widget);
				}
				logger.debug("Setting handler field {} to widget with id {}",
						field.getName(), widget.getId());
				field.set(handler, widget);
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
