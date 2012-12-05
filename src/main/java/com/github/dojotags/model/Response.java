package com.github.dojotags.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response implements Serializable {
	private static final long serialVersionUID = 1L;
	List<Widget> updates;

	public Response() {
		updates = new ArrayList<Widget>();
	}
	
	public List<Widget> getUpdates() {
		return updates;
	}

	public void setUpdates(List<Widget> updates) {
		this.updates = updates;
	}

	@Override
	public String toString() {
		return "Response [updates=" + updates + "]";
	}

}
