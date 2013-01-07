package com.github.dojotags.test.app.config;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.github.dojotags.test.web.mvvm.Page1View;

/**
 * Configuration context for the test web application.
 * 
 * @author gushakov
 * 
 */
@Configuration
public class AppConfig {
	private static final Logger logger = LoggerFactory
			.getLogger(AppConfig.class);

	@Bean
	public MessageSource messageSource() {
		StaticMessageSource messageSource = new StaticMessageSource();
		messageSource.addMessage(
				"com.github.dojotags.test.web.validation.name", Locale.US,
				"Name is incorrect");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource(messageSource());
		return validatorFactoryBean;
	}
	
	
	// MVVM, model view session-scoped beans
	
	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Page1View page1View(){
		logger.debug("Initializing com.github.dojotags.test.web.mvvm.Page1View view-model");
		return new Page1View();
	}
	
}
