package com.github.dojotags.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public abstract class AbstractSubmitTag extends SimpleTagSupport {
	
	private String action;
	
	public void setAction(String action) {
		this.action = action;
	}
	
	protected String getDojoType() {
		// subclasses should override returning actual Dojo widget type
		return null;
	}

	protected String getDojoEvent() {
		// subclasses should override with actual Dojo event
		return Constants.DOJO_EVENT_ON_CLICK;
	}
	
	protected void addTemplateAttribute(Map<String, Object> attributes){
		// subclasses can override
	}

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();

		JspTag parentTag = getParent();
		if (parentTag == null || !(parentTag instanceof FormTag)) {
			throw new JspException(
					"Submit tag should be embedded into a form tag. This tag's parent tag is "
							+ parentTag + ".");
		}

		String formName = ((FormTag) parentTag).getName();

		String actionText = null;
		if(action != null){
			actionText = action.trim();
		}
		else {
			actionText = "";
		}
		
		try {

			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("dojoType", getDojoType());
			attrs.put("dojoEvent", getDojoEvent());
			attrs.put("formName", formName);
			attrs.put("action", actionText);
			addTemplateAttribute(attrs);
			out.println(TagTemplates.substitute("submit", attrs));
		} catch (Exception e) {
			throw new JspException(e);
		}

	}

}
