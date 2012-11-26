package com.github.dojotags.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWidgetTag extends SimpleTagSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(AbstractWidgetTag.class);

	protected String widgetId;

	protected boolean assertHasParentTag;

	public AbstractWidgetTag() {
		assertHasParentTag = false;
	}

	public String getWidgetId() {
		return widgetId;
	}

	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}

	public boolean isAssertHasParentTag() {
		return assertHasParentTag;
	}

	public void setAssertHasParentTag(boolean assertHasParentTag) {
		this.assertHasParentTag = assertHasParentTag;
	}

	@Override
	public void doTag() throws JspException, IOException {
		logger.debug("Parsing a widget tag {}", this.getClass().getName());

		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();

		// construct a map of attributes to be substituted in the templates
		Map<String, Object> attrs = new HashMap<String, Object>();

		// add context attribute
		String contextPath = ((HttpServletRequest) pageContext.getRequest())
				.getContextPath();
		attrs.put("contextPath", contextPath);

		// add widget id attribute
		String widgetId = getWidgetId().trim();
		if (widgetId.equals("")) {
			throw new JspException("Tag's widgetId attribute is empty.");
		}
		attrs.put("widgetId", widgetId);

		// check if this tag is embedded into a parent form tag
		JspTag parentTag = getParent();
		if (assertHasParentTag) {

			if (parentTag == null) {
				throw new JspException("Tag of type "
						+ this.getClass().getName()
						+ " should be embedded in a container tag.");
			} else {
				// add parent widget attribute
				AbstractWidgetTag parentWidgetTag = (AbstractWidgetTag) parentTag;
				attrs.put("parentId", parentWidgetTag.getWidgetId());

			}
		}

		// add other template attributes
		addTemplateAttributes(attrs);

		// output the contents of this tag
		doTagContentOutput(out, attrs);

	}

	protected void addTemplateAttributes(Map<String, Object> attrs)
			throws JspException {
		// add custom tag attributes
	}

	protected void doTagContentOutput(JspWriter out, Map<String, Object> attrs)
			throws JspException {
		throw new JspException(new OperationNotSupportedException());
	}

}
