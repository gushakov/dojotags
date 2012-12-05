package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

/**
 * Tag handler for {@code Button} widget.
 * 
 * @author George Ushakov
 * 
 */
public class ButtonTag extends AbstractWidgetTag {
	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "button";
	public static final String WIDGET_MODULE_NAME = "Button";

	private String label;

	public void setLabel(String label) {
		this.label = label;
	}

	public ButtonTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("button");
		setAssertHasParentTag(true);
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		if (label != null) {
			label = label.trim();
		} else {
			label = "";
		}
		templateAttrs.put("label", label);
		return result;
	}

}
