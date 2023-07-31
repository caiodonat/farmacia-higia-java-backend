package com.farmaciahigia.schemas.customer;

import java.util.Date;
import java.util.List;

import com.farmaciahigia.schemas.ISchema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRequest implements ISchema {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("cpf")
	private String cpf;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("birthDate")
	private Date birthDate;
	
	
	public CustomerRequest() {
	}
	
	@JsonCreator
	public CustomerRequest(
	@JsonProperty(namespace = "id", required = true) int id,
	@JsonProperty(namespace = "cpf", required = false) String cpf,
	@JsonProperty(namespace = "password", required = false) String password,
	@JsonProperty(namespace = "email", required = false) String email,
	@JsonProperty(namespace = "firstName", required = false) String firstname,
	@JsonProperty(namespace = "lastName", required = false) String lastname,
	@JsonProperty(namespace = "phone", required = false) String phone,
	@JsonProperty(namespace = "birthDate", required = false) Date birthDate
	) {
		this.id = id;
		this.cpf = cpf;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.birthDate = birthDate;
	}
	
	
}
