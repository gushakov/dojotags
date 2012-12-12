package com.github.dojotags.test.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.github.dojotags.json.Response;
import com.github.dojotags.test.form.Person;
import com.github.dojotags.web.AbstractDojoTagsController;
import com.github.dojotags.web.annotation.WidgetEventMapping;
import com.github.dojotags.widgets.Button;
import com.github.dojotags.widgets.Label;
import com.github.dojotags.widgets.Widget;

@Controller
public class TestController extends AbstractDojoTagsController {
	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);

	@WidgetEventMapping(widgetId = "btn1", event = "click")
	public Response changeLabelText(Widget widget) {
		logger.debug("Processing click event on btn1 with widget model {}",
				widget);
		Button buttonModel = (Button) widget;
		Response response = new Response();
		Label labelUpdate = new Label();
		labelUpdate.setId("lbl1");
		labelUpdate.setText("This label was updated by a click on a button "
				+ buttonModel.getLabel());
		response.getUpdates().add(labelUpdate);
		return response;
	}

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
