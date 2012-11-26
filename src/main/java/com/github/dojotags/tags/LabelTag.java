package com.github.dojotags.tags;

import java.util.Map;

import javax.servlet.jsp.JspException;

public class LabelTag extends AbstractEmptyBodyWidgetTag {

	private String text;

	public void setText(String text) {
		this.text = text;
	}

	public LabelTag() {
		setTagTemplate("label");
		setAssertHasParentTag(true);
	}
	
	@Override
	protected void addTemplateAttributes(Map<String, Object> attrs)
			throws JspException {
		// get text
		String textStr = null;
		if (text != null) {
			textStr = text.trim();
		} else {
			textStr = "";
		}
		attrs.put("text", textStr);
	}

}
