package com.github.dojotags.widgets;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.github.dojotags.utils.Items;

public class Select extends Widget {

	private static final long serialVersionUID = 1L;

	private Integer value = -1;

	private String path;

	private String regExp;

	@JsonIgnore
	private Items addItems;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRegExp() {
		return regExp;
	}

	public void setRegExp(String regExp) {
		this.regExp = regExp;
	}

	@JsonProperty
	public Items getAddItems() {
		return addItems;
	}

	@JsonIgnore
	public void setAddItems(Items addItems) {
		this.addItems = addItems;
	}

}
