package com.github.dojotags.tags;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

public abstract class AbstractEmptyBodyWidgetTag extends AbstractWidgetTag {
	
	protected String tagTemplate;
	
	public void setTagTemplate(String tagTemplate) {
		this.tagTemplate = tagTemplate;
	}
	
	@Override
	protected void doTagContentOutput(JspWriter out, Map<String, Object> attrs)
			throws JspException {
		try {
			out.println(TagTemplates.substitute(tagTemplate, attrs));
		} catch (IOException e) {
			throw new JspException(e);
		}
	}
	
}
