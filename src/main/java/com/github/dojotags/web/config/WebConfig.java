package com.github.dojotags.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Web MVC configuration to be processed by
 * {@code AnnotationConfigWebApplicationContext} declared with
 * {@code DispatcherServlet}.
 * 
 * @author George Ushakov
 * @see org.springframework.web.context.support.AnnotationConfigWebApplicationContext
 * @see org.springframework.web.servlet.config.annotation.EnableWebMvc
 * @see org.springframework.web.servlet.DispatcherServlet
 * 
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.github.dojotags.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	// set up the default view resolver, mapping logical view name
	// to a JSP with the same file name
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	// add handlers for the static resources (JavaScript, CSS, images),
	// will look up Dojo files from the JAR on the classpath
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/META-INF/resources/")
				.setCachePeriod(31556926);
	}

	// set up the redirection to the main view if the root URL is accessed
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/notags").setViewName("notags");
		registry.addViewController("/withtags").setViewName("withtags");
	}
}
