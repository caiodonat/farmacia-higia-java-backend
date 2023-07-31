package com.farmaciahigia.schemas.product;

import java.util.List;

public class ProductResponseError {
	
	private String message;

	private List<String> errors;

	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}

}
