package com.github.dojotags.test.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.dojotags.json.Response;
import com.github.dojotags.web.AbstractDojoTagsController;
import com.github.dojotags.web.annotation.WidgetEventMapping;
import com.github.dojotags.widgets.Input;
import com.github.dojotags.widgets.Label;

@Controller
@RequestMapping(value = "/page2")
public class Page2Controller extends AbstractDojoTagsController {
	private static final Logger logger = LoggerFactory
			.getLogger(Page2Controller.class);

	@RequestMapping(method = RequestMethod.GET)
	public String showPage2() {
		return "page2";
	}
	
	@WidgetEventMapping(widgetId = "inp1", event = "enter")
	public Response processOnEnter(Input input) {
		logger.debug("Processing enter event on input {}", input.getId());
		Response response = new Response();
		Label label = new Label();
		label.setId("lbl1");
		label.setText("Hello, " + input.getValue());
		response.getUpdates().add(label);
		return response;
	}

}
