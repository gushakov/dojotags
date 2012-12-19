package com.github.dojotags.widgets;

/**
 * Encapsulates model attributes for {@code Label} widget.
 * 
 * @author gushakov
 * 
 */
public class Label extends Widget {
	private static final long serialVersionUID = 1L;

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
