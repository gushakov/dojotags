package com.github.dojotags.tags;

/**
 * Tag handler for Form widget.
 * @author George Ushakov
 */
public class FormTag extends AbstractJspBodyWidgetTag {
    private static final long serialVersionUID = 1L;

    public static final String WIDGET_NAME = "form";
    public static final String WIDGET_MODULE_NAME = "Form";


    public FormTag(){
        setWidgetName(WIDGET_NAME);
        setWidgetModuleName(WIDGET_MODULE_NAME);
        setTagBeginTemplate("form-begin");
        setAssertHasParentTag(true);
    }

}
