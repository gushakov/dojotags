package com.github.dojotags.model;

public class Label extends Widget {
	private static final long serialVersionUID = 1L;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Label [text=" + text + ", id=" + getId() + "]";
	}

	
}
