package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dojotags.utils.WidgetUtils;

/**
 * Superclass for all widget tags. Specifies attributes common to all widget
 * tags. Registers this tag with the ancestor tag of type {@code Page}.
 * Automatically assigns a unique id to the missing {@code widgetId} attribute.
 * 
 * @author George Ushakov
 * @see PageTag#registerNestedTag(AbstractWidgetTag)
 * 
 */
public abstract class AbstractWidgetTag extends AbstractTemplatedTag {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractWidgetTag.class);

	protected String widgetName;

	protected String widgetModuleName;

	protected String widgetId;

	protected String widgetClass;

	protected boolean assertHasParentTag;

	public AbstractWidgetTag() {
		assertHasParentTag = false;
	}

	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}

	public void setWidgetModuleName(String widgetModuleName) {
		this.widgetModuleName = widgetModuleName;
	}

	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}

	public void setWidgetClass(String widgetClass) {
		this.widgetClass = widgetClass;
	}

	public boolean isAssertHasParentTag() {
		return assertHasParentTag;
	}

	public void setAssertHasParentTag(boolean assertHasParentTag) {
		this.assertHasParentTag = assertHasParentTag;
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();
		if (widgetId == null) {
			// create a unique widget id attribute automatically
			widgetId = WidgetUtils.getWidgetGuid(widgetName);
		}
		templateAttrs.put("widgetId", widgetId);

		templateAttrs.put("widgetClass", widgetClass);

		// do for all tags nested in a page tag
		if (!widgetName.equals(PageTag.WIDGET_NAME)) {
			// find the ancestor page tag and register this tag as a nested tag
			PageTag pageTag = (PageTag) findAncestorWithClass(this,
					PageTag.class);
			if (pageTag == null) {
				throw new JspException("No ancestor page tag found for tag "
						+ this);
			}
			pageTag.registerNestedTag(this);
			logger.debug(
					"Registered nested tag {} in the ancestor page tag {}",
					this, pageTag);
		}

		// check if this tag is embedded into a parent form tag
		JspTag parentTag = getParent();
		if (isAssertHasParentTag()) {

			if (parentTag == null) {
				throw new JspException("Tag of type "
						+ this.getClass().getName()
						+ " should be embedded in a container tag.");
			} else {
				// add parent widget id attribute
				AbstractWidgetTag parentWidgetTag = (AbstractWidgetTag) parentTag;
				templateAttrs.put("parentId", parentWidgetTag.widgetId);
			}
		}

		return result;
	}

	protected void resetWidgetAttributes() {
		// reset widget id to null so that it is automatically generated for the
		// next widget with no widget id attribute set
		widgetId = null;
	}

	@Override
	public int doEndTag() throws JspException {
		resetWidgetAttributes();
		return super.doEndTag();
	}

}
