package com.github.dojotags.widgets;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Widget implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
