package com.github.dojotags.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Superclass for all template driven tags. Has a reference to
 * {@code tagBeginTemplate} file template and {@code templateAttrs} map which
 * will be populated with tag attributes during execution of
 * {@code doStartTag()} method. Defaults to appending the string resulting from
 * the substitution of {@code tagBeginTemplate} with {@code templateAttrs} to
 * the current {@code JspWriter}. Proceeds with {@code EVAL_BODY_BUFFERED} after
 * {@code doStartTag()} and with {@code EVAL_PAGE} after {@code doEndTag()}.
 * 
 * @author gushakov
 * @see TagTemplates#substitute(String, Map)
 */
public abstract class AbstractTemplatedTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	protected String tagBeginTemplate;

	protected Map<String, Object> templateAttrs;

	public String getTagBeginTemplate() {
		return tagBeginTemplate;
	}

	public void setTagBeginTemplate(String tagBeginTemplate) {
		this.tagBeginTemplate = tagBeginTemplate;
	}

	@Override
	public int doStartTag() throws JspException {

		templateAttrs = new HashMap<String, Object>();

		// add context attribute
		if (HttpServletRequest.class.isAssignableFrom(pageContext.getRequest()
				.getClass())) {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			String contextPath = request.getContextPath();
			templateAttrs.put("contextPath", contextPath);
			String pagePath = request.getAttribute("javax.servlet.forward.servlet_path").toString();
			templateAttrs.put("pagePath", pagePath);
		} else {
			throw new JspException("Not implemented for request of type "
					+ pageContext.getRequest().getClass().getName());
		}

		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().append(
					TagTemplates.substitute(tagBeginTemplate, templateAttrs));
		} catch (IOException e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

}
