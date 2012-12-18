package com.github.dojotags.tags;


/**
 * Tag handler for {@code Form} widget.
 * 
 * @author gushakov
 */
public class FormTag extends AbstractJspBodyWidgetTag implements
		BindableWidgetTag {

	private static final long serialVersionUID = 1L;

	public static final String WIDGET_NAME = "form";
	public static final String WIDGET_MODULE_NAME = "Form";

	private Object bean;

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public FormTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("form");
		setAssertHasParentTag(true);
	}

	@Override
	public String getBindClassName() {
		return bean.getClass().getName();
	}
}
