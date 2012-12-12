package com.github.dojotags.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.dojotags.widgets.Widget;

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
