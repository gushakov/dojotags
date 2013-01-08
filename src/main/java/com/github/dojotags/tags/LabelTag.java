package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

/**
 * Tag handler for {@code Label} widget.
 * 
 * @author gushakov
 * 
 */
public class LabelTag extends AbstractWidgetTag {
	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "label";
	public static final String WIDGET_MODULE_NAME = "Label";

	private String text;

	public void setText(String text) {
		this.text = text;
	}

	public LabelTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("label");
		setAssertHasParentTag(true);
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		templateAttrs.put("text", text);
		return result;
	}
}
