package com.farmaciahigia.model;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.farmaciahigia.schemas.customer.CustomerCore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; 

@Entity
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class Customer extends CustomerCore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 11, nullable = false, unique = true)
	private String cpf;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	@Column(name = "avatar_url")
	private String avatarUrl;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@Column(name = "recover_code", length = 6)
	private String recoverCode;
	
	@Column(name = "is_active", columnDefinition = "default true")
	private Boolean isActive;
	
	
	// constructors
	public Customer() {
	};
	
	public Customer(CustomerCore customer) {
		System.out.println(customer.toString());
		
		this.cpf = customer.getCpf();
		this.email = customer.getEmail();
		this.password = customer.getPassword();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.phone = customer.getPhone();
		this.birthDate = customer.getBirthDate();
	};
	
	public Customer(Customer customer) {
		System.out.println("Customer -> Customer");
		this.id = customer.getId();
		this.cpf = customer.getCpf();
		this.email = customer.getEmail();
		this.password = customer.getPassword();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.phone = customer.getPhone();
		this.birthDate = customer.getBirthDate();
		this.recoverCode = customer.getRecoverCode();
		this.isActive = customer.getIsActive();
	}
	
	// Methods

	@Override
	public String toString() {
		ObjectMapper objectMapper = new ObjectMapper();

		String json;
		try {
			json = objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			json = "";
		}

		return json;
	}
	
	public void setPasswordCrypt() {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		this.password = String.valueOf(bCrypt.encode(this.password));
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getRecoverCode() {
		return recoverCode;
	}

	public void setRecoverCode(String recoverCode) {
		this.recoverCode = recoverCode;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
	