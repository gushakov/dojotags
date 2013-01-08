package com.github.dojotags.test.web.mvvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dojotags.web.annotation.WidgetEvent;

public class Page1View {
	private static final Logger logger = LoggerFactory
			.getLogger(Page1View.class);

	private String firstName;

	private String greeting;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	@WidgetEvent(value = "changeFirstName", updates = { "greeting" })
	public void onEnter() {
		logger.debug("Entered new first name {}", firstName);
		greeting = "Hello " + firstName;
	}

}
