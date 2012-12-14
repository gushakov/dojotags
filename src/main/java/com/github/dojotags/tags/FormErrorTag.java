package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

public class FormErrorTag extends AbstractFormElementWidgetTag {

	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "error";
	public static final String WIDGET_MODULE_NAME = "FormError";

	public FormErrorTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("error");
		setAssertHasParentTag(true);
	}

}
