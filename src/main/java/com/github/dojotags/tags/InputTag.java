package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;


/**
 * Tag handler for {@code Input} widget.
 * 
 * @author gushakov
 * 
 */
public class InputTag extends AbstractWidgetTag {

	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "input";
	public static final String WIDGET_MODULE_NAME = "Input";

	public InputTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("input");
		setAssertHasParentTag(true);
	}
	
	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		//TODO: allow setting of the initial value
		templateAttrs.put("value", "");
		return result;
	}

}
