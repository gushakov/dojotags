package com.github.dojotags.test.web.mvvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dojotags.web.annotation.WidgetEvent;
import com.github.dojotags.widgets.Input;

public class Page1View {
	private static final Logger logger = LoggerFactory
			.getLogger(Page1View.class);
	
	private Input firstName;
	
	public Input getFirstName() {
		return firstName;
	}

	public void setFirstName(Input firstName) {
		this.firstName = firstName;
	}

	@WidgetEvent("enter")
	public void onEnter(){
		if (firstName!=null){
			logger.debug("Entered new first name {}", firstName.getValue());			
		}		
	}
	
}
