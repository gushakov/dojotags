package com.github.dojotags.model;

@SuppressWarnings("serial")
public class Label extends Widget {
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
