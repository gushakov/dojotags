package com.github.dojotags.model;

public class Button extends Widget {

	private static final long serialVersionUID = 1L;

	public String getLabel() {
		return (String) model.get("label");
	}

	public void setLabel(String label) {
		model.put("label", label);
	}

}
