package com.github.dojotags.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;

import com.github.dojotags.web.AbstractDojoTagsController;
import com.github.dojotags.widgets.Widget;

/**
 * Bean encapsulating the response information returned after each Ajax request
 * to the server. It is divided in several parts: {@code response.updates}
 * contains the list of widgets to be updated, {@code response.errors} contains
 * a map of error objects keyed by the form backing object property names
 * (paths). Will be automatically converted to Json via {@code ResponseBody}
 * annotation on the widget event handling method.
 * 
 * @author gushakov
 * 
 * @see {@linkplain AbstractDojoTagsController#processWidgetEvent(String, String, Object)}
 * @see {@linkplain ResponseBody}
 * @see {@code dojotags._Widget#processCallback}
 * 
 */
public class Response implements Serializable {
	private static final long serialVersionUID = 1L;
	List<Widget> updates;
	Map<String, Object> errors;

	public Response() {
		updates = new ArrayList<Widget>();
		errors = new HashMap<String, Object>();
	}

	public List<Widget> getUpdates() {
		return updates;
	}

	public void setUpdates(List<Widget> updates) {
		this.updates = updates;
	}

	public Map<String, Object> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, Object> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "Response [updates=" + updates + "]";
	}

}
