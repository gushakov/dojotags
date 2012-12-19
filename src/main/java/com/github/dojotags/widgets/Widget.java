package com.github.dojotags.widgets;

import java.io.Serializable;

/**
 * Encapsulates model attributes common to all widgets.
 * 
 * @author gushakov
 * 
 */
public class Widget implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
