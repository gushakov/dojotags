package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tag handler for {@code Button} widget.
 * 
 * @author George Ushakov
 * 
 */
public class ButtonTag extends AbstractWidgetTag {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(ButtonTag.class);
	public static final String WIDGET_NAME = "button";
	public static final String WIDGET_MODULE_NAME = "Button";

	private String label;

	private String onClick;

	public void setLabel(String label) {
		this.label = label;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
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
		templateAttrs.put("label", label);
		templateAttrs.put("onClick", onClick);
		return result;
	}

}
