package com.github.dojotags.model;

public class Input extends Widget {

	private static final long serialVersionUID = 1L;

	public String getValue() {
		return (String) model.get("value");
	}

	public void setValue(String value) {
		model.put("value", value);
	}

	public String getPath() {
		return (String) model.get("path");
	}

	public void setPath(String path) {
		model.put("path", path);
	}

}
