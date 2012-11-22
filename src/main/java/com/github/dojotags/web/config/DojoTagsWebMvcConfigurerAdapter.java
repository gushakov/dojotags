package com.github.dojotags.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan(basePackages = "com.github.dojotags.web")
public abstract class DojoTagsWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	// add handlers for the static resources (JavaScript, CSS, images),
	// will look up Dojo files from the JAR on the classpath
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/META-INF/resources/")
				.setCachePeriod(31556926);
	}

}
