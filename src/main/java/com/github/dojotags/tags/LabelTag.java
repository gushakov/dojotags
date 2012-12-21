package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

import com.github.dojotags.utils.WidgetUtils;
import com.github.dojotags.web.registry.WidgetsRegistry;
import com.github.dojotags.widgets.Label;

/**
 * Tag handler for {@code Label} widget.
 * 
 * @author gushakov
 * 
 */
public class LabelTag extends AbstractWidgetTag {
	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "label";
	public static final String WIDGET_MODULE_NAME = "Label";

	private String text;

	public void setText(String text) {
		this.text = text;
	}

	public LabelTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("label");
		setAssertHasParentTag(true);
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		WidgetsRegistry widgetsRegistry = WidgetUtils
				.getWidgetsRegistry(pageContext);
		Label label = widgetsRegistry.get(id, Label.class);
		if (label != null) {
			templateAttrs.put("text", label.getText());
		} else {
			label = new Label();
			label.setId(id);
			label.setText(text);
			widgetsRegistry.put(label);
			templateAttrs.put("text", text);
		}
		return result;
	}
}
