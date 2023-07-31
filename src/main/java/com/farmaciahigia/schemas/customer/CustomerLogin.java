package com.farmaciahigia.schemas.customer;

import java.util.ArrayList;
import java.util.List;

import com.farmaciahigia.schemas.ISchema;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class CustomerLogin implements ISchema {
	private String password = "12qwaszx";
	
	private String email = "nobody@mail.com";
	
	protected List<String> errors = new ArrayList<>();
	
	// methods
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<String> errors() {
		return this.errors;
	}
}
