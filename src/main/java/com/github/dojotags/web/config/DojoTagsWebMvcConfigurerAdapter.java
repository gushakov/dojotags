package com.github.dojotags.web.config;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public abstract class DojoTagsWebMvcConfigurerAdapter extends
		WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(DojoTagsWebMvcConfigurerAdapter.class);

	// Allow subclasses to override to inject the common ObjectMapper
	// instance from the root web application context
	public ObjectMapper getJacksonObjectMapper() {
		ObjectMapper jacksonMapper = new ObjectMapper();
		logger.debug(
				"Initialized Jackson mapper bean in the applicationcontext {}",
				jacksonMapper);
		return jacksonMapper;
	}

	// Add argument resolver for WidgetBody annotated parameters
	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		WidgetBodyHandlerMethodArgumentResolver resolver = new WidgetBodyHandlerMethodArgumentResolver();
		resolver.setJacksonMapper(getJacksonObjectMapper());
		argumentResolvers.add(resolver);
	}

	// Add handlers for the static resources (JavaScript, CSS, images),
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/META-INF/resources/",
						"/resources/**").setCachePeriod(31556926);

	}

}
