package com.github.dojotags.tags;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dojotags.utils.Utils;

/**
 * Superclass for all widget tag handlers. Specifies attributes common to all
 * widget tags. Registers this tag with the ancestor tag of type {@code Page}.
 * Automatically assigns a unique id to the missing {@code id} attribute.
 * 
 * @author gushakov
 * @see PageTag#registerNestedTag(AbstractWidgetTag)
 * 
 */
public abstract class AbstractWidgetTag extends AbstractTemplatedTag {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractWidgetTag.class);

	protected String widgetName;

	protected String widgetModuleName;

	protected String id;

	protected String name;

	protected String styleClass;

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

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
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
		if (id == null) {
			// get the value for widget id automatically
			id = Utils.getWidgetGuid(widgetName, pageContext);
		}
		templateAttrs.put("id", id);

		templateAttrs.put("name", name);

		templateAttrs.put("styleClass", styleClass);

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
				templateAttrs.put("parent", parentWidgetTag.id);
			}
		}

		return result;
	}

	protected void resetWidgetAttributes() {
		// reset widget id to null so that it is automatically generated for the
		// next widget with no widget id attribute set
		id = null;
	}

	@Override
	public int doEndTag() throws JspException {
		resetWidgetAttributes();
		return super.doEndTag();
	}

	public Object getInitialValue() throws JspException {
		Object value = null;
		if (name != null) {
			PageTag pageTag = (PageTag) findAncestorWithClass(this,
					PageTag.class);
			if (pageTag != null) {
				Object viewModel = pageTag.getViewModel();
				if (viewModel != null) {
					Method getter = Utils.findMehod(Utils.getGetter(name),
							viewModel.getClass().getMethods());
					if (getter != null) {
						try {
							value = getter.invoke(viewModel);
						} catch (IllegalArgumentException e) {
							throw new JspException(e);
						} catch (IllegalAccessException e) {
							throw new JspException(e);
						} catch (InvocationTargetException e) {
							throw new JspException(e);
						}
					}
				}
			}
		}
		return value;
	}

}
