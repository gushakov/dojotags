package com.github.dojotags.test.web;

import java.util.Date;

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

/**
 * Controller for a test page.
 * 
 * @author gushakov
 * 
 */
@Controller
@RequestMapping(value = "/page6")
public class Page6Controller extends AbstractDojoTagsController {
	private static final Logger logger = LoggerFactory
			.getLogger(Page6Controller.class);

	@RequestMapping(method = RequestMethod.GET)
	public String showPage6() {
		Label label = new Label();
		label.setId("lbl1");
		label.setText("George");
		widgetsRegistry.put(label);
		return "page6";
	}

	@WidgetEventMapping(widgetId = "btn1", event = "click")
	public Response changeLabelText(Button button) {
		logger.debug("Processing click event on button {}", button.getId());
		Label label = widgetsRegistry.get("lbl1", Label.class);
		logger.debug("Label lbl1 text {}", label.getText());
		Response response = new Response();
		label.setText("This label was updated by a click on a button "
				+ button.getId() + " " + new Date());
		response.getUpdates().add(label);
		return response;
	}

}
