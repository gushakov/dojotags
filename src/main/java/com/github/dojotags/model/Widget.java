package com.github.dojotags.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Widget implements Serializable {
	
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
