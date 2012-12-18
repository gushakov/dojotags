package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

import com.github.dojotags.utils.WidgetUtils;

/**
 * Tag handler for {@code Rows} widget container.
 * 
 * @author gushakov
 * 
 */
public class RowsTag extends AbstractJspBodyWidgetTag {

	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "rows";
	public static final String WIDGET_MODULE_NAME = "Rows";

	private String spacer;

	public void setSpacer(String height) {
		this.spacer = height;
	}

	public RowsTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("rows");
		setAssertHasParentTag(true);
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		if (!WidgetUtils.assertValidCssUnitOfMeasure(spacer)) {
			spacer = "";
		}
		templateAttrs.put("spacer", spacer);
		return result;
	}
}
