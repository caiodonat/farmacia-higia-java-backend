package com.farmaciahigia.schemas.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.farmaciahigia.schemas.ISchema;

import io.swagger.v3.oas.annotations.media.Schema;

public class CustomerCore implements ISchema {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String cpf = "12345678910";

	private String password = "12qwaszx";

	private String email = "nobody@mail.com";

	private String firstName;

	private String lastName;

	private String phone;

	private Date birthDate;

	protected List<String> errors = new ArrayList<>();

	// methods

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<String> errors() {
		return this.errors;
	}
}
