package com.github.dojotags.tags;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;

/**
 * Tag handler for all widget tags which can be embedded inside a {@code Form}
 * tag. Declares {@code path} attribute which references a property of the form
 * backing object. The value of this property will be then specified as the
 * value of {@code value} attribute. If {@code path} is not set, {@code value}
 * attribute can be used to specify an initial value of a widget.
 * 
 * @author gushakov
 * 
 */
public abstract class AbstractFormElementWidgetTag extends AbstractWidgetTag {

	private static final long serialVersionUID = 1L;

	protected String path;

	protected String value;

	public void setPath(String path) {
		this.path = path;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();

		if (path != null) {
			templateAttrs.put("path", path);
			// get the parent form tag
			FormTag formTag = (FormTag) findAncestorWithClass(this,
					FormTag.class);
			if (formTag != null) {
				// get the value of the property of the backing bean
				// corresponding to path
				Object bean = formTag.getBean();
				try {
					templateAttrs.put(
							"value",
							bean.getClass()
									.getMethod("get" + toProperCase(path))
									.invoke(bean));
				} catch (IllegalArgumentException e) {
					throw new JspException(e);
				} catch (SecurityException e) {
					throw new JspException(e);
				} catch (IllegalAccessException e) {
					throw new JspException(e);
				} catch (InvocationTargetException e) {
					throw new JspException(e);
				} catch (NoSuchMethodException e) {
					throw new JspException(e);
				}
			} else {
				throw new JspException("Cannot find ancestor FormTag for tag "
						+ id);
			}
		} else {
			// default to the value specified as the tag's attribute
			templateAttrs.put("value", value);
			templateAttrs.put("path", "");
		}
		return result;
	}

	private String toProperCase(String path) {
		return path.substring(0, 1).toUpperCase() + path.substring(1);
	}

}
