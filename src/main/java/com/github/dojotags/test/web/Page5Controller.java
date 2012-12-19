package com.github.dojotags.test.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.dojotags.json.Items;
import com.github.dojotags.json.Response;
import com.github.dojotags.web.AbstractDojoTagsController;
import com.github.dojotags.web.annotation.WidgetEventMapping;
import com.github.dojotags.widgets.Select;
import com.google.common.collect.ImmutableMap;

/**
 * Controller for a test page.
 * 
 * @author gushakov
 * 
 */
@Controller
@RequestMapping(value = "/page5")
public class Page5Controller extends AbstractDojoTagsController {
	private static final Logger logger = LoggerFactory
			.getLogger(Page5Controller.class);

	@RequestMapping(method = RequestMethod.GET)
	public String showPage5() {
		return "page5";
	}

	@ModelAttribute("items")
	public Items initSelect() {
		return new Items(ImmutableMap.of(1, "one", 2, "two", 3, "three", 4,
				"four"));
	}

	@WidgetEventMapping(widgetId = "sel1", event = "open")
	public Response updateSelect(Select select) {
		logger.debug("Processing select update with regExp {}",
				select.getRegExp());
		Response response = new Response();
		Select update = new Select();
		update.setAddItems(new Items(ImmutableMap.of(5, "five", 6, "six", 7,
				"seven", 8, "eight")));
		response.getUpdates().add(update);
		return response;
	}

}
