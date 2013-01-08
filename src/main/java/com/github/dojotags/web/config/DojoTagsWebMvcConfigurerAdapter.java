package com.github.dojotags.web.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Registers resources handler for JavaScript files.
 * 
 * @author gushakov
 * 
 */
public abstract class DojoTagsWebMvcConfigurerAdapter extends
		WebMvcConfigurerAdapter {

	// Add handlers for the static resources (JavaScript, CSS, images),
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/META-INF/resources/",
						"/resources/**").setCachePeriod(31556926);

	}

}
