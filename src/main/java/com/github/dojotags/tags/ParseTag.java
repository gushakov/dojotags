package com.github.dojotags.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseTag extends SimpleTagSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(ParseTag.class);

	@Override
	public void doTag() throws JspException, IOException {
		logger.debug("Parsing parse tag.");
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();

		try {

			out.println(TagTemplates.getTemplate("parse"));

		} catch (Exception e) {
			throw new JspException(e);
		}

	}

}
