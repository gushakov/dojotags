package com.github.dojotags.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/submit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
public class FormController {
	private static final Logger logger = LoggerFactory
			.getLogger(FormController.class);

	@RequestMapping(value = "/{form}")
	public @ResponseBody
	Map<String, Object> simple(@PathVariable("form") String formName,
			@RequestBody Map<String, Object> model) {
		logger.debug(
				"Processing Ajax request /submit with form name {}, action {}, and model {}.",
				new Object[] { formName, model });

		model.put("message", "Processed request /submit/" + formName + ".");
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{form}/{action}")
	public @ResponseBody
	Map<String, Object> grid(@PathVariable("form") String formName,
			@PathVariable("action") String action,
			@RequestBody Map<String, Object> model) {
		logger.debug(
				"Processing Ajax request /submit with form name {}, action {}, and model {}.",
				new Object[] { formName, action, model });

		if (action != null && action.equals("addNewItem")) {
			List<Map<String, Object>> data = (List<Map<String, Object>>) model
					.get("grd1_data");
			if (data == null) {
				data = new ArrayList<Map<String, Object>>();
				model.put("grd1_data", data);
			}
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("col1", "new");
			item.put("col2", "item");
			data.add(item);

			model.put("message", "Added new item to the data of the grid grd1.");
		} else if (action != null && action.equals("select")) {
			List<Map<String, Object>> selection = (List<Map<String, Object>>) model
					.get("grd1_selection");
			logger.debug("Current selection {}", selection);
			model.put("message", "Processed selection changed event.");
		} else {
			model.put("message", "Processed request /submit/" + formName + "/"
					+ action + ".");
		}
		return model;
	}

}
