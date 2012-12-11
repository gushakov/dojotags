package com.github.dojotags.test.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.github.dojotags.model.Button;
import com.github.dojotags.model.Label;
import com.github.dojotags.model.Response;
import com.github.dojotags.model.Widget;
import com.github.dojotags.web.AbstractDojoTagsController;
import com.github.dojotags.web.annotation.WidgetEventMapping;

@Controller
public class TestController extends AbstractDojoTagsController {
	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);

	@WidgetEventMapping(widgetId = "btn1", event = "click")
	public Response changeLabelText(Widget widgetModel) {
		logger.debug("Processing click event on btn1 with widget model {}",
				widgetModel);
		Button buttonModel = (Button) widgetModel;
		Response response = new Response();
		Label labelUpdate = new Label();
		labelUpdate.setId("lbl1");
		labelUpdate.setText("This label was updated by a click on a button "
				+ buttonModel.getLabel());
		response.getUpdates().add(labelUpdate);
		return response;
	}

	@WidgetEventMapping(widgetId = "frm1", event = "submit")
	public Response formSubmit(Widget widgetModel) {
		Response response = new Response();
		String firstName = (String) widgetModel.getModel().get("firstName");
		
		logger.debug("Processing from submit: firstName {}", firstName);
		
		if(! firstName.matches("\\p{Alpha}+")){
			response.getErrors().put("firstName", "Name should contain letter characters only.");			
		}
		
		return response;
	}

}
