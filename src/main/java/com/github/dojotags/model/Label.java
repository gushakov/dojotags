package com.github.dojotags.model;

public class Label extends Widget {
	private static final long serialVersionUID = 1L;

	public String getText() {
		return (String) model.get("text");
	}

	public void setText(String text) {
		model.put("text", text);
	}
	
}
