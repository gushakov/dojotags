package com.github.dojotags.tags;

import java.util.Map;

import javax.servlet.jsp.JspException;

import com.github.dojotags.utils.Assert;

public class RowsTag extends AbstractScriptlessBodyWidgetTag {
	public static final String WIDGET_NAME = "rows";

	private String spacerHeight;

	public void setSpacerHeight(String spacerHeight) {
		this.spacerHeight = spacerHeight;
	}

	public RowsTag() {
		setWidgetName(WIDGET_NAME);
		setTagBeginTemplate("rows-begin");
		setAssertHasParentTag(true);
	}

	@Override
	protected void addTemplateAttributes(Map<String, Object> attrs)
			throws JspException {
		String spacerHeightStr = null;
		if (spacerHeight != null) {
			spacerHeightStr = spacerHeight.trim();
			if (!Assert.assertValidCssUnitOfMeasure(spacerHeightStr)) {
				throw new JspException(new IllegalArgumentException("Spacer "
						+ spacerHeightStr
						+ " height is not a CSS unit of measure."));
			}
		} else {
			spacerHeightStr = "";
		}
		attrs.put("spacerHeight", spacerHeightStr);
	}

}
