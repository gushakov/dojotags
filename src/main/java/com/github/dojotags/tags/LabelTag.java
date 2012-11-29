package com.github.dojotags.tags;

import java.util.Map;

import javax.servlet.jsp.JspException;

public class LabelTag extends AbstractEmptyBodyWidgetTag {

	public static final String WIDGET_NAME = "label";

	private String text;

	public void setText(String text) {
		this.text = text;
	}

	public LabelTag() {
		setWidgetName(WIDGET_NAME);
		setTagTemplate("label");
		setAssertHasParentTag(true);
	}
	
	@Override
	protected void addTemplateAttributes(Map<String, Object> attrs)
			throws JspException {
		// add text attribute
		if (text != null) {
			text = text.trim();
		} else {
			text = "";
		}
		attrs.put("text", text);
	}

}
