package com.github.dojotags.tags;

import java.io.StringWriter;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;

public abstract class AbstractScriptlessBodyWidgetTag extends AbstractWidgetTag {

	protected String tagBeginTemplate;

	protected String tagEndTemplate;

	public void setTagBeginTemplate(String tagBeginTemplate) {
		this.tagBeginTemplate = tagBeginTemplate;
	}

	public void setTagEndTemplate(String tagEndTemplate) {
		this.tagEndTemplate = tagEndTemplate;
	}

	protected void doTagContentOutput(JspWriter out, Map<String, Object> attrs)
			throws JspException {
		try {
			JspFragment body = getJspBody();
			StringWriter writer = new StringWriter();
			writer.append(TagTemplates.substitute(tagBeginTemplate, attrs));
			body.invoke(writer);
			writer.append(TagTemplates.substitute(tagEndTemplate, attrs));
			out.println(writer);

		} catch (Exception e) {
			throw new JspException(e);
		}

	}
}
