package com.github.dojotags.model;

@SuppressWarnings("serial")
public class Button extends Widget {

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
