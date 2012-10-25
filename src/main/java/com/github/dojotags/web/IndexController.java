package com.github.dojotags.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(method = RequestMethod.GET)
public class IndexController {

	@RequestMapping(value = "/notags", method = RequestMethod.GET)
	public String notags() {
		return "notags";
	}

	@RequestMapping(value = "/withtags", method = RequestMethod.GET)
	public String withtags() {
		return "withtags";
	}

	
}
