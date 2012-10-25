package com.github.dojotags.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutputTag extends SimpleTagSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(OutputTag.class);
	protected String path;

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void doTag() throws JspException, IOException {
		logger.debug("Parsing output tag with path {}.", path);

		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();

		// check if this tag is embedded into a parent form tag
		JspTag formTag = getParent();
		if (formTag == null || !(formTag instanceof FormTag)) {
			throw new JspException(
					"Output tag should be embedded into a form tag. This tag's parent tag is "
							+ formTag + ".");
		}

		// binding path should not be empty
		if (path == null || path.matches("\\s*")) {
			throw new JspException("Path attribute cannot be null or empty");
		}

		String outputPath = path.trim();

		try {

			out.println("<span data-dojo-type=\"dojox/mvc/Output\" data-dojo-props=\"value: at('rel:', '"
					+ outputPath + "')\"></span>");

		} catch (Exception e) {
			throw new JspException(e);
		}
	}
}
