package com.github.dojotags.model;

public class Button extends Widget {

	private static final long serialVersionUID = 1L;
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "Button [label=" + label + ", id=" + getId() + "]";
	}
		
}
