package com.farmaciahigia.schemas.product;

import com.farmaciahigia.model.Customer;

public class ProductResponseSuccess {
	
	private String message;

	private Customer content;

	public String getMessage() {
		return message;
	}

	public Customer getContent() {
		return content;
	}

}
