package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

import com.github.dojotags.utils.Assert;

public class FlowTag extends AbstractJspBodyWidgetTag {

	private static final long serialVersionUID = 1L;
	
	public static final String WIDGET_NAME = "flow";
	public static final String WIDGET_MODULE_NAME = "Flow";

	private String spacerWidth;

	public void setSpacerWidth(String spacerWidth) {
		this.spacerWidth = spacerWidth;
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
		if (!Assert.assertValidCssUnitOfMeasure(spacerWidth)) {
			spacerWidth = "";
		}
		templateAttrs.put("spacerWidth", spacerWidth);			
		return result;
	}

}
