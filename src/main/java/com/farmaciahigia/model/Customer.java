package com.farmaciahigia.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Customer implements Serializable {

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

	private Integer phone;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "recover_code", length = 6)
	private String recoverCode;

	@Column(name = "is_active", columnDefinition = "boolean default true")
	private Boolean isActive = true;

	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "address_id", referencedColumnName = "id")
	// @Column(nullable = false)
	// private Address address;

	// Methods
	@Override
	public String toString() {
		return String.format(
				"Customer[id=%d, p='%s', e='%s']",
				id, password, email);
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

	public void setPasswordCrypt(String password) {
		// this.password = "password";
		this.password = String.valueOf(new BCryptPasswordEncoder().encode(password));
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
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

	// public Address getAddress() {
	// return address;
	// }

	// public void setAddress(Address address) {
	// this.address = address;
	// }
}