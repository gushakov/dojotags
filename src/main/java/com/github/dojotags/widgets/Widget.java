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
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
