package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

import com.github.dojotags.utils.Assert;

/**
 * Tag handler for {@code Rows} widget container.
 * 
 * @author George Ushakov
 * 
 */
public class RowsTag extends AbstractJspBodyWidgetTag {

	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "rows";
	public static final String WIDGET_MODULE_NAME = "Rows";

	private String spacerHeight;

	public void setSpacerHeight(String spacerHeight) {
		this.spacerHeight = spacerHeight;
	}

	public RowsTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("rows-begin");
		setAssertHasParentTag(true);
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		if (spacerHeight != null) {
			spacerHeight = spacerHeight.trim();
			if (!Assert.assertValidCssUnitOfMeasure(spacerHeight)) {
				throw new JspException(new IllegalArgumentException("Spacer "
						+ spacerHeight
						+ " height is not a CSS unit of measure."));
			}
		} else {
			spacerHeight = "";
		}
		templateAttrs.put("spacerHeight", spacerHeight);
		return result;
	}
}
