package com.farmaciahigia.model;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.farmaciahigia.schemas.customer.CustomerCore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
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

	// constructors
	public Customer() {
	};
	
	public Customer(CustomerCore customer) {
		this.cpf = customer.getCpf();
		this.email = customer.getEmail();
		this.password = customer.getPassword();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.phone = customer.getPhone();
		this.birthDate = customer.getBirthDate();
	};

	public Customer(Customer customer) {
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPasswordCrypt(String password) {
		this.password = String.valueOf(new BCryptPasswordEncoder().encode(password));
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