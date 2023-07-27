package com.farmaciahigia.schemas.customer;

import com.farmaciahigia.model.Customer;

public class CustomerResponseSuccess {
	
	private String message;

	private Customer content;


	public String getMessage() {
		return message;
	}

	public Customer getContent() {
		return content;
	}

}
