package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final Logger logger = LoggerFactory
			.getLogger(LabelTag.class);
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
		WidgetsRegistry registry = WidgetUtils.getWidgetsRegistry(pageContext);
		Label label = registry.get(id, Label.class);
		logger.debug("Looked up label {} from widgets registry.", label);
		if (text == null){
			templateAttrs.put("text", label.getText());
		}
		else {
			label.setText(text);
			templateAttrs.put("text", text);
		}
		return result;
	}
}
