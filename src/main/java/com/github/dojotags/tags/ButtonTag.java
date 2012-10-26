package com.github.dojotags.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class ButtonTag extends AbstractSubmitTag {
	private String label;

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	protected String getDojoType() {
		return "dijit/form/Button";
	}

	@Override
	protected void addTemplateAttribute(Map<String, Object> attributes) {
		String labelText = null;
		if (label != null) {
			labelText = label.trim();
		} else {
			labelText = Constants.BUTTON_LABEL_DEFAULT;
		}
		attributes.put("label", labelText);
	}
}
