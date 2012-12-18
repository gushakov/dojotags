package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

import com.github.dojotags.widgets.Input;

/**
 * Tag handler for {@code Input} widget.
 * 
 * @author gushakov
 * 
 */
public class InputTag extends AbstractFormElementWidgetTag implements
		BindableWidgetTag {

	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "input";
	public static final String WIDGET_MODULE_NAME = "Input";

	private String onenter;

	public void setOnenter(String onenter) {
		this.onenter = onenter;
	}

	public InputTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("input");
		setAssertHasParentTag(true);
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		templateAttrs.put("onenter", onenter);
		return result;
	}

	@Override
	public String getBindClassName() {
		return Input.class.getName();
	}

}
