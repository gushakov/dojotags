package com.github.dojotags.test.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.github.dojotags.web.config.DojoTagsWebMvcConfigurerAdapter;

/**
 * Web configuration context used in the test web application.
 * 
 * @author gushakov
 * 
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.github.dojotags.test.web" })
public class WebConfig extends DojoTagsWebMvcConfigurerAdapter {

	// set up the default view resolver, mapping logical view name
	// to a JSP with the same file name
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	// set up the redirection to the main view if the root URL is accessed
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/notags").setViewName("notags");
	}

}
