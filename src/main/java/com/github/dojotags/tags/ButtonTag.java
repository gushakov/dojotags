package com.github.dojotags.tags;

import java.util.Map;

import javax.servlet.jsp.JspException;

public class ButtonTag extends AbstractEmptyBodyWidgetTag {
	public static final String WIDGET_NAME = "button";
	
	private String label;

	public void setLabel(String label) {
		this.label = label;
	}

	public ButtonTag() {
		setWidgetName(WIDGET_NAME);
		setTagTemplate("button");
		setAssertHasParentTag(true);
	}
	
	@Override
	protected void addTemplateAttributes(Map<String, Object> attrs)
			throws JspException {
		// get label
		String labelStr = null;
		if (label != null) {
			labelStr = label.trim();
		} else {
			labelStr = "";
		}
		attrs.put("label", labelStr);
	}

}
