package com.github.dojotags.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormTag extends SimpleTagSupport {

	private static final Logger logger = LoggerFactory.getLogger(FormTag.class);

	private String name;

	private String actionPath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}

	@Override
	public void doTag() throws JspException, IOException {
		logger.debug(
				"Parsing from tag with name {}, action path {}, and model data {}",
				new Object[] { name, actionPath });

		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();
		String contextPath = ((HttpServletRequest) pageContext.getRequest())
				.getContextPath();
		JspFragment body = getJspBody();

		if (name == null || name.matches("\\s*")) {
			throw new JspException("Form name cannot be null or empty");
		}

		String formName = name.trim();

		try {
			
			StringWriter writer = new StringWriter();
			writer.append("<script>" + "require([\""
					+ Constants.TAGLIB_JS_NAMESPACE
					+ "/Form\", \"dojo/_base/kernel"
					+ "\"], function(Form, kernel){" + "if(kernel.global[\""
					+ formName + "\"] === undefined){" + "kernel.global[\""
					+ formName + "\"] = new Form({");
			if (actionPath != null) {
				writer.append("actionPath:\"" + contextPath + actionPath + "\"");
			}
			writer.append("});	"
					+ "} else {"
					+ "throw new Error(\"Form with name "
					+ formName
					+ " exists already.\");"
					+ "}});"
					+ "</script>"
					+ "<div data-dojo-type=\"dojox/mvc/Group\" data-dojo-props=\"target: "
					+ formName + ".model\">");
			body.invoke(writer);
			writer.append("</div>");
			out.println(writer);

		} catch (Exception e) {
			throw new JspException(e);
		}
	}

}
