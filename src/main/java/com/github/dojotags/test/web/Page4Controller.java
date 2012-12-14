package com.github.dojotags.test.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.dojotags.json.Response;
import com.github.dojotags.test.form.Person;
import com.github.dojotags.web.AbstractDojoTagsController;
import com.github.dojotags.web.annotation.WidgetEventMapping;
import com.github.dojotags.widgets.Label;

@Controller
@RequestMapping(value = "/page4")
public class Page4Controller extends AbstractDojoTagsController {
	private static final Logger logger = LoggerFactory
			.getLogger(Page4Controller.class);

	@RequestMapping(method = RequestMethod.GET)
	public String showPage4() {
		return "page4";
	}

	@ModelAttribute("person")
	public Person initPersonForm() {
		Person person = new Person();
		person.setFirstName("Toto");
		person.setLastName("Toto");
		logger.debug("Initialized Person form backing bean: {}", person);
		return person;
	}

	@WidgetEventMapping(widgetId = "frm1", event = "submit")
	public Response formSubmit(Person form) {
		Response response = new Response();
		logger.debug("Processing from submit: firstName {}, lastName {}",
				form.getFirstName(), form.getLastName());
		String firstName = form.getFirstName();
		if (!firstName.matches("\\p{Alpha}+")) {
			response.getErrors().put("firstName",
					"Name should contain letter characters only.");
		} else {
			Label label = new Label();
			label.setId("lbl1");
			label.setText("Form was successfully submited.");
			response.getUpdates().add(label);
		}
		return response;
	}
}
