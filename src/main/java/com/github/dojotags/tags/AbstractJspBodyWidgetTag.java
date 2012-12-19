package com.github.dojotags.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * Handler for tags with {@code body-content} of type {@code JSP}. Declares a
 * reference to {@code tagEndTemplate} file template. Implements
 * {@code doInitBody()} and {@code doAfterBody} to substitute
 * {@code tagBeginTemplate} and {@code tagEndTemplate} respectively with
 * template attributes and append them to {@code bodyContent}.
 * 
 * @author gushakov
 * 
 */
public abstract class AbstractJspBodyWidgetTag extends AbstractWidgetTag {

	private static final long serialVersionUID = 1L;
	protected String tagEndTemplate;

	public void setTagEndTemplate(String tagEndTemplate) {
		this.tagEndTemplate = tagEndTemplate;
	}

	@Override
	public void doInitBody() throws JspException {
		try {
			getBodyContent().append(
					TagTemplates.substitute(tagBeginTemplate, templateAttrs));
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		if (tagEndTemplate != null) {
			try {
				getBodyContent().append(
						TagTemplates.substitute(tagEndTemplate, templateAttrs));
			} catch (IOException e) {
				throw new JspException(e);
			}
		}
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		super.resetWidgetAttributes();
		try {
			getBodyContent().getEnclosingWriter().println(
					modifyBodyContentBeforeOutput());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

	/**
	 * Allow subclasses to implement custom modification needed to be done on
	 * the {@code bodyContent} before it is actually appended to the current
	 * {@code JspWriter}.
	 * 
	 * @return {@code bodyContent} serialized as a stirng
	 */
	protected String modifyBodyContentBeforeOutput() {
		return getBodyContent().getString();
	}

}
