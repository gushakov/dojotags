package com.github.dojotags.test.form;

import java.io.Serializable;

import com.github.dojotags.test.web.validation.Name;

/**
 * Form backing object used in examples.
 * 
 * @author gushakov
 * 
 */
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Name
	private String firstName;

	@Name
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
