package com.github.dojotags.widgets;

// mvvm branch

/**
 * Encapsulates model attributes for {@code Button} widget.
 * 
 * @author gushakov
 * 
 */
public class Button extends Widget {

	private static final long serialVersionUID = 1L;

	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
