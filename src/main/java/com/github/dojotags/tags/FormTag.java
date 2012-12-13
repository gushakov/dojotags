package com.github.dojotags.tags;

/**
 * Tag handler for {@code Form} widget.
 * 
 * @author George Ushakov
 */
public class FormTag extends AbstractJspBodyWidgetTag implements
		BindableWidgetTag {
	private static final long serialVersionUID = 1L;

	public static final String WIDGET_NAME = "form";
	public static final String WIDGET_MODULE_NAME = "Form";

	private String bind;

	public void setBind(String bind) {
		this.bind = bind;
	}

	public FormTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("form-begin");
		setAssertHasParentTag(true);
	}

	@Override
	public String getBindClassName() {
		return bind;
	}
}
