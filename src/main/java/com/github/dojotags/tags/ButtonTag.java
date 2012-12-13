package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

import com.github.dojotags.widgets.Button;

/**
 * Tag handler for {@code Button} widget.
 * 
 * @author George Ushakov
 * 
 */
public class ButtonTag extends AbstractWidgetTag implements BindableWidgetTag {
	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "button";
	public static final String WIDGET_MODULE_NAME = "Button";

	private String label;

	private String onclick;

	public void setLabel(String label) {
		this.label = label;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
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
		templateAttrs.put("onclick", onclick);
		return result;
	}

	@Override
	public String getBindClassName() {
		return Button.class.getName();
	}
}
