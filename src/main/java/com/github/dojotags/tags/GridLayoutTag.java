package com.github.dojotags.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GridLayoutTag extends SimpleTagSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(GridLayoutTag.class);

	@Override
	public void doTag() throws JspException, IOException {
		logger.debug("Parsing gridLayout tag.");
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();
		JspFragment body = getJspBody();

		JspTag parentTag = getParent();
		if (parentTag == null || !(parentTag instanceof GridTag)) {
			throw new JspException(
					"Submit tag should be embedded into a grid tag. This tag's parent tag is "
							+ parentTag + ".");
		}

		StringWriter writer = new StringWriter();
		writer.append(TagTemplates.getTemplate("grid-layout-begin"));
		body.invoke(writer);
		writer.append(TagTemplates.getTemplate("grid-layout-end"));
		out.println(writer);

	}
}
