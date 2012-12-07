package com.github.dojotags.model;

import java.io.Serializable;
import java.util.Map;

public class Widget implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String id;

	protected Map<String, Object> model;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
