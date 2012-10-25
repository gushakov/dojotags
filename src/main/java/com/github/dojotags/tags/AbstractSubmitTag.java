package com.github.dojotags.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public abstract class AbstractSubmitTag extends SimpleTagSupport {

	public static final String DOJO_EVENT_DEFAULT = "onClick";

	protected String getDojoType() {
		// subclasses should override returning actual Dojo widget type
		return null;
	}

	protected String getDojoEvent() {
		// subclasses should override with actual Dojo event
		return DOJO_EVENT_DEFAULT;
	}

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();
		JspFragment body = getJspBody();

		JspTag formTag = getParent();
		if (formTag == null || !(formTag instanceof FormTag)) {
			throw new JspException(
					"Submit tag should be embedded into a form tag. This tag's parent tag is "
							+ formTag + ".");
		}

		String formName = ((FormTag) formTag).getName();

		try {
			StringWriter writer = new StringWriter();

			writer.append("<div data-dojo-type=\"" + getDojoType()
					+ "\"><script type=\"dojo/connect\" data-dojo-event=\""
					+ getDojoEvent() + "\">" + formName + ".submit();</script>");
			body.invoke(writer);
			writer.append("</div>");
			out.println(writer);

		} catch (Exception e) {
			throw new JspException(e);
		}

	}

}
