package com.github.dojotags.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MvcController {
	private static final Logger logger = LoggerFactory.getLogger(MvcController.class);

	@RequestMapping(value = "/notags", method = RequestMethod.GET)
	public String notags() {
		logger.debug("Processing GET request /notags");
		return "notags";
	}

	@RequestMapping(value = "/withtags", method = RequestMethod.GET)
	public String withtags() {
		logger.debug("Processing GET request /withtags");
		return "withtags";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/submit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody
	Map<String, Object> submit(@RequestBody Map<String, Object> model) {
		logger.debug("Processing Ajax request /submit, model: {}", model);
		
		List<Map<String, Object>> data = (List<Map<String, Object>>) model.get("data");
		if (data != null){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", 4);
			item.put("col1", "new");
			item.put("col2", "item");
			data.add(item);
		}
		
		model.put("message", "This is a result of /submit processing.");
		return model;
	}

	@RequestMapping(value = "/error", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody
	Map<String, Object> error(@RequestBody Map<String, Object> model) {
		logger.debug("Processing Ajax request /submit, model: {}", model);
		model.put("error", "Error occured on during request processing.");
		return model;
	}

}
