package com.github.dojotags.tags;

import java.util.Map;

import javax.servlet.jsp.JspException;

import com.github.dojotags.utils.Assert;

public class FlowTag extends AbstractScriptlessBodyWidgetTag {
	public static final String WIDGET_NAME = "flow";

	private String spacerWidth;

	public void setSpacerWidth(String spacerWidth) {
		this.spacerWidth = spacerWidth;
	}

	public FlowTag() {
		setWidgetName(WIDGET_NAME);
		setTagBeginTemplate("flow-begin");
		setAssertHasParentTag(true);
	}

	@Override
	protected void addTemplateAttributes(Map<String, Object> attrs)
			throws JspException {
		if (spacerWidth != null) {
			spacerWidth = spacerWidth.trim();
			if (!Assert.assertValidCssUnitOfMeasure(spacerWidth)) {
				throw new JspException(new IllegalArgumentException("Spacer "
						+ spacerWidth
						+ " width is not a CSS unit of measure."));
			}
		} else {
			spacerWidth = "";
		}
		attrs.put("spacerWidth", spacerWidth);
	}

}
