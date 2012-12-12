package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

public class ErrorTag extends AbstractWidgetTag {

	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "error";
	public static final String WIDGET_MODULE_NAME = "Error";

	private String path;

	public void setPath(String path) {
		this.path = path;
	}

	public ErrorTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("error");
		setAssertHasParentTag(true);
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		templateAttrs.put("path", path);
		return result;
	}

}