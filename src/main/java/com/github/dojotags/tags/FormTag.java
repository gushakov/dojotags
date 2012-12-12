package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

/**
 * Tag handler for Form widget.
 * 
 * @author George Ushakov
 */
public class FormTag extends AbstractJspBodyWidgetTag {
	private static final long serialVersionUID = 1L;

	public static final String WIDGET_NAME = "form";
	public static final String WIDGET_MODULE_NAME = "Form";

	private String formClass;

	public void setFormClass(String formClass) {
		this.formClass = formClass;
	}

	public FormTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("form-begin");
		setAssertHasParentTag(true);
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		templateAttrs.put("formClass", formClass);
		return result;
	}
}
