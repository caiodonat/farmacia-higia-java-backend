package com.farmaciahigia.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Customer implements Serializable {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
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
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordCrypt(String password) {
		// SecureRandom myEncoder = new SecureRandom();
		// myEncoder.getInstance("12qwaszx");
		
		System.out.println(password);
		// BCryptPasswordEncoder crypto = new BCryptPasswordEncoder(10, new SecureRandom().getInstance("12qwaszx"));


		// this.password = "password";
		this.password = String.valueOf(new BCryptPasswordEncoder().encode(password));
	}

	public void cryptPassword() {
		System.out.println(this.password);

		this.password = String.valueOf(new BCryptPasswordEncoder().encode(this.password));
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
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