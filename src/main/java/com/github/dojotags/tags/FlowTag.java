package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

import com.github.dojotags.utils.WidgetUtils;

public class FlowTag extends AbstractJspBodyWidgetTag {

	private static final long serialVersionUID = 1L;

	public static final String WIDGET_NAME = "flow";
	public static final String WIDGET_MODULE_NAME = "Flow";

	private String spacer;

	public void setSpacer(String width) {
		this.spacer = width;
	}

	public FlowTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("flow-begin");
		setAssertHasParentTag(true);
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		if (!WidgetUtils.assertValidCssUnitOfMeasure(spacer)) {
			spacer = "";
		}
		templateAttrs.put("spacerWidth", spacer);
		return result;
	}

}
