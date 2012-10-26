package com.github.dojotags.tags;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GridTag extends SimpleTagSupport {

	private static final Logger logger = LoggerFactory.getLogger(GridTag.class);

	private String name;

	private String width;

	private String height;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Override
	public void doTag() throws JspException, IOException {
		logger.debug("Parsing grid tag with name {}, width {}, and height {}.",
				new Object[] { name, width, height });
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();
		JspFragment body = getJspBody();

		JspTag parentTag = getParent();
		if (parentTag == null || !(parentTag instanceof FormTag)) {
			throw new JspException(
					"Submit tag should be embedded into a form tag. This tag's parent tag is "
							+ parentTag + ".");
		}

		String formName = ((FormTag) parentTag).getName();

		if (name == null || name.matches("\\s*")) {
			throw new JspException("Form name cannot be null or empty");
		}

		String widthText = null;
		if (width != null) {
			widthText = width.trim();
		} else {
			widthText = Constants.GRID_WIDTH_DEFAULT;
		}

		String heightText = null;
		if (height != null) {
			heightText = height.trim();
		} else {
			heightText = Constants.GRID_HEIGHT_DEFAULT;
		}

		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("gridName", name);
		attrs.put("gridWidth", widthText);
		attrs.put("gridHeight", heightText);
		attrs.put("formName", formName);
		StringWriter writer = new StringWriter();
		writer.append(TagTemplates.substitute("grid-begin", attrs));
		body.invoke(writer);
		writer.append(TagTemplates.substitute("grid-end", attrs));
		out.println(writer);

	}
}