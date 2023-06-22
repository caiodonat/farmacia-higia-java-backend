package com.farmaciahigia.model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart implements Serializable {

	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String uuid;

	@Column(length = 11, nullable = false, unique = true)
	private String cpf;

	@Column(nullable = false, unique = true)
	private String email;

	// constructors
	public Cart() {
	}

	// Methods

}