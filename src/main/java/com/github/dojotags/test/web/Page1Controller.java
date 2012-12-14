package com.github.dojotags.test.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.dojotags.json.Response;
import com.github.dojotags.web.AbstractDojoTagsController;
import com.github.dojotags.web.annotation.WidgetEventMapping;
import com.github.dojotags.widgets.Button;
import com.github.dojotags.widgets.Label;

@Controller
@RequestMapping(value = "/page1")
public class Page1Controller extends AbstractDojoTagsController {
	private static final Logger logger = LoggerFactory
			.getLogger(Page1Controller.class);

	@RequestMapping(method = RequestMethod.GET)
	public String showPage1() {
		return "page1";
	}
	
	@WidgetEventMapping(widgetId = "btn1", event = "click")
	public Response changeLabelText(Button button) {
		logger.debug("Processing click event on button {}", button.getId());
		Response response = new Response();
		Label label = new Label();
		label.setId("lbl1");
		label.setText("This label was updated by a click on a button "
				+ button.getId());
		response.getUpdates().add(label);
		return response;
	}

}
