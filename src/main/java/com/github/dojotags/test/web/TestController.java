package com.github.dojotags.test.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.github.dojotags.web.AbstractDojoTagsController;
import com.github.dojotags.web.annotation.DojoTag;

@Controller
public class TestController extends AbstractDojoTagsController {
	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);
	
	@DojoTag("btn1")
	public Map<String, Object> doOnButtonClick(Map<String, Object> model){
		logger.debug("Processing click event on btn1 with model {}", model);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("message", "Hello World");
		return data;
	}
	
}
