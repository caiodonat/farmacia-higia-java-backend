package com.farmaciahigia.schemas.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.farmaciahigia.schemas.ISchema;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class CustomerCore implements ISchema {
	
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
	
	protected List<String> errors = new ArrayList<>();
	
	// methods
	
	public CustomerCore(){	
	}
	
	public CustomerCore(String cpf, String password, String email, String firstName, String lastName, String phone,
	Date birthDate) {
		this.cpf = cpf;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.birthDate = birthDate;
	}
	
	@Override
	public String toString() {
		return "CustomerCore [cpf=" + cpf + ", password=" + password + ", email=" + email + ", firstName=" + firstName
		+ ", lastName=" + lastName + ", phone=" + phone + ", birthDate=" + birthDate + ", errors=" + errors + "]";
	}
	
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
		return errors;
	}
}
