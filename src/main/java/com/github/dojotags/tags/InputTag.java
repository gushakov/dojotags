package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

import com.github.dojotags.widgets.Input;

/**
 * Tag handler for {@code Input} widget.
 * 
 * @author George Ushakov
 * 
 */
public class InputTag extends AbstractWidgetTag implements BindableWidgetTag {

	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "input";
	public static final String WIDGET_MODULE_NAME = "Input";

	private String path;

	private String value;

	private String onenter;

	public void setPath(String path) {
		this.path = path;
	}

	public void setValue(String value) {
		this.value = value;
	}

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
		templateAttrs.put("path", path);
		templateAttrs.put("value", value);
		templateAttrs.put("onenter", onenter);
		return result;
	}

	@Override
	public String getBindClassName() {
		return Input.class.getName();
	}

}
