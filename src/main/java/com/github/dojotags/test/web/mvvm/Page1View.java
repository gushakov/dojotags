package com.github.dojotags.test.web.mvvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dojotags.web.annotation.WidgetEvent;

public class Page1View {
	private static final Logger logger = LoggerFactory
			.getLogger(Page1View.class);

	private String firstName;
	
	private String lastName;

	private String greeting;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
	public void init(){
		greeting = "Initial value";
	}

	@WidgetEvent(value = "greet", updates = { "greeting" })
	public void greet() {
		logger.debug("Processing greet event first name {}, last name {}", firstName, lastName);
		greeting = "Hello " + firstName + " " + lastName;
	}

}
