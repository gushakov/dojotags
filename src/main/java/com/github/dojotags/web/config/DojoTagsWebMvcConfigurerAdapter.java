package com.github.dojotags.web.config;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Registers a custom web argument resolver,
 * {@linkplain WidgetBodyHandlerMethodArgumentResolver} and resources handler
 * for JavaScript files.
 * 
 * @author gushakov
 * 
 */
public abstract class DojoTagsWebMvcConfigurerAdapter extends
		WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(DojoTagsWebMvcConfigurerAdapter.class);

	// Add argument resolver for WidgetBody annotated parameters
	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		WidgetBodyHandlerMethodArgumentResolver resolver = new WidgetBodyHandlerMethodArgumentResolver();
		resolver.setJacksonMapper(new ObjectMapper());
		argumentResolvers.add(resolver);
	}

	// Add handlers for the static resources (JavaScript, CSS, images),
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/META-INF/resources/",
						"/resources/**").setCachePeriod(31556926);

	}
	
	// Add handler interceptor which will bind the widgets from the registry
	// to the annotated fields of the handler
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new WidgetsBindingHandlerInterceptor());
	}

}
