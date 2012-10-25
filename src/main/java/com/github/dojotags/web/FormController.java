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
@RequestMapping(value="/form", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
public class FormController {
	private static final Logger logger = LoggerFactory.getLogger(FormController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/submit")
	public @ResponseBody
	Map<String, Object> submit(@RequestBody Map<String, Object> model) {
		logger.debug("Processing Ajax request /submit, model: {}", model);
		
		List<Map<String, Object>> data = (List<Map<String, Object>>) model.get("data");
		if (data != null){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("col1", "new");
			item.put("col2", "item");
			data.add(item);
		}
		
		model.put("message", "This is a result of /submit processing.");
		return model;
	}

}
