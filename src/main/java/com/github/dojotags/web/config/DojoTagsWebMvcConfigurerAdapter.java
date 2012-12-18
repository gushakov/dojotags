package com.github.dojotags.web.config;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public abstract class DojoTagsWebMvcConfigurerAdapter extends
		WebMvcConfigurerAdapter {

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

}
