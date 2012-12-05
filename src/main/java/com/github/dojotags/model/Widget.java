package com.github.dojotags.model;

import java.io.Serializable;

public class Widget implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Widget [id=" + id + "]";
	}
	
}
