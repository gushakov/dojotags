package com.github.dojotags.tags;

/**
 * Handler for tag used to output the error message related to the validation of
 * the form backing bean's property specified as value of {@code path}
 * attribute.
 * 
 * @author gushakov
 * 
 */
public class FormErrorTag extends AbstractFormElementWidgetTag {

	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "error";
	public static final String WIDGET_MODULE_NAME = "FormError";

	public FormErrorTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("error");
		setAssertHasParentTag(true);
	}

}
