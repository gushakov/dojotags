package com.github.dojotags.widgets;

public class Input extends FormElement {

	private static final long serialVersionUID = 1L;

	private String value;
	
	private String path;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
