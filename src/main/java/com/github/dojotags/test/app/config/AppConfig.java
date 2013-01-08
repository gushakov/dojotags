package com.github.dojotags.test.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.github.dojotags.test.web.mvvm.Page1View;
import com.github.dojotags.web.annotation.ViewModel;

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

	// View-model beans
	
	@ViewModel
	public Page1View page1View() {
		logger.debug("Initializing com.github.dojotags.test.web.mvvm.Page1View view-model");
		return new Page1View();
	}

}
