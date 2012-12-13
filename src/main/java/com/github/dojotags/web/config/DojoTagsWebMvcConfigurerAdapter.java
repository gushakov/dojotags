package com.github.dojotags.web.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan(basePackages = "com.github.dojotags.web")
public abstract class DojoTagsWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	// Add handlers for the static resources (JavaScript, CSS, images),
	// will look up Dojo files from the JAR on the classpath
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/META-INF/resources/", "/resources/**")
				.setCachePeriod(31556926);
	}
	
	// Add argument resolver for WidgetBody annotated parameters
	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new WidgetBodyHandlerMethodArgumentResolver());
	}

}
