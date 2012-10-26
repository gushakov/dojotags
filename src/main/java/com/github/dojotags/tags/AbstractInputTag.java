package com.github.dojotags.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractInputTag extends SimpleTagSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(AbstractInputTag.class);

	protected String path;

	public void setPath(String path) {
		this.path = path;
	}

	protected String getDojoType() {
		// subclasses should override returning actual Dojo widget type
		return null;
	}

	@Override
	public void doTag() throws JspException, IOException {
		logger.debug("Parsing input tag with path {}", path);

		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();

		// check if this tag is embedded into a parent form tag
		JspTag parentTag = getParent();
		if (parentTag == null || !(parentTag instanceof FormTag)) {
			throw new JspException(
					"Submit tag should be embedded into a form tag. This tag's parent tag is "
							+ parentTag + ".");
		}

		// binding path should not be empty
		if (path == null || path.matches("\\s*")) {
			throw new JspException("Path attribute cannot be null or empty");
		}

		String inputPath = path.trim();

		try {

			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("dojoType", getDojoType());
			attrs.put("path", inputPath);
			out.println(TagTemplates.substitute("input", attrs));

		} catch (Exception e) {
			throw new JspException(e);
		}

	}

}
