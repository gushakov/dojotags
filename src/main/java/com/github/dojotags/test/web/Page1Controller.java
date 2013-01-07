package com.github.dojotags.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.dojotags.web.AbstractDojoTagsController;

@Controller
@RequestMapping(value = "/page1")
public class Page1Controller extends AbstractDojoTagsController  {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showPage1(){
		return "page1";
	}
	
}
