package com.farmaciahigia.schemas.customer;

import java.util.List;

public class CustomerResponseError {
	
	private String message;

	private List<String> errors;



	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}

}
