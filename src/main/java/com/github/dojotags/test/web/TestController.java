package com.github.dojotags.test.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.github.dojotags.json.Response;
import com.github.dojotags.test.form.Person;
import com.github.dojotags.web.AbstractDojoTagsController;
import com.github.dojotags.web.annotation.WidgetEventMapping;
import com.github.dojotags.widgets.Button;
import com.github.dojotags.widgets.Input;
import com.github.dojotags.widgets.Label;

@Controller
public class TestController extends AbstractDojoTagsController {
	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);

	// page 1

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

	// page 2

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

	// page 3

	@WidgetEventMapping(widgetId = "frm1", event = "submit")
	public Response formSubmit(Person form) {
		Response response = new Response();
		String firstName = form.getFirstName();
		logger.debug("Processing from submit: firstName {}", firstName);
		if (!firstName.matches("\\p{Alpha}+")) {
			response.getErrors().put("firstName",
					"Name should contain letter characters only.");
		}
		return response;
	}

}
