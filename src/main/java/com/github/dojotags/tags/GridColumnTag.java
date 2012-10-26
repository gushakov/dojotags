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

public class GridColumnTag extends SimpleTagSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(GridColumnTag.class);
	
	private String field;
	
	private String name;

	private String width;
	
	public void setField(String field) {
		this.field = field;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWidth(String width) {
		this.width = width;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		logger.debug("Parsing gridColumn tag with field {}, name {}, and width {}.",
				new Object[]{field, name, width});
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();
		
		// this tag should be embedded in to a gridLayout tag
		JspTag parentTag = getParent();
		if (parentTag == null || !(parentTag instanceof GridLayoutTag)) {
			throw new JspException(
					"Submit tag should be embedded into a gridLayout tag. This tag's parent tag is "
							+ parentTag + ".");
		}
		
		if (field == null || field.matches("\\s*")){
			throw new JspException("Field attribute must not be null or empty");
		}
		
		String widthText = null;
		if (width != null){
			widthText = width.trim();
		}
		else {
			widthText = Constants.GRID_COLUMN_WIDTH_DEFAULT;
		}

		String nameText = null;
		if (name != null){
			nameText = name.trim();
		}
		else {
			nameText = field;
		}

		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("field", field);
		attrs.put("width", widthText);
		attrs.put("name", nameText);
		
		out.println(TagTemplates.substitute("grid-column", attrs));

	}
	
}
