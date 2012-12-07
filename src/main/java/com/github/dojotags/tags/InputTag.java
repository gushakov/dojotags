package com.github.dojotags.tags;

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tag handler for Input widget.
 * 
 * @author George Ushakov
 * 
 */
public class InputTag extends AbstractWidgetTag {

	private static final Logger logger = LoggerFactory
			.getLogger(InputTag.class);
	
	private static final long serialVersionUID = 1L;
	public static final String WIDGET_NAME = "input";
	public static final String WIDGET_MODULE_NAME = "Input";

	private String value;

	private String onEnter;
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setOnEnter(String onEnter) {
		this.onEnter = onEnter;
	}

	public InputTag() {
		setWidgetName(WIDGET_NAME);
		setWidgetModuleName(WIDGET_MODULE_NAME);
		setTagBeginTemplate("input");
		setAssertHasParentTag(true);
	}

	@Override
	public int doStartTag() throws JspException {
		int result = super.doStartTag();

		templateAttrs.put("value", value);

		if(onEnter!=null){
			if (onEnter.trim().equals("submit")){
				onEnter = onEnter.trim();
			}
			else {
				logger.warn("Unknown value for onEnter attribute {}, setting attribute value to default instead.", onEnter);
				onEnter = "default";
			}
		}
		else {
			onEnter = "default";
		}
		templateAttrs.put("onEnter", onEnter);
		
		return result;
	}

}
