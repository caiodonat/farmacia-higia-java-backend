package com.farmaciahigia.model;

import java.util.List;

import com.farmaciahigia.schemas.product.ProductCore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity(name = "product")
public class Product extends ProductCore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String type;

	private String description;

	@Column(unique = true, nullable = false)
	private String ean;

	private Double value;

	@Column(name = "sale_fee")
	private Double saleFee; // desconto em % (0 ~ 100)

	@ManyToMany()
	// @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
	// @PrimaryKeyJoinColumn
	private List<Storage> storages;

	// Constructor
	public Product() {

	}

	public Product(ProductCore product) {
		// setTypeValidator(product.getType());
		this.description = product.getDescription();
		this.ean = product.getEan();
		this.value = product.getValue();
		this.saleFee = product.getSaleFee();
	};

	public Product(String type, String description, String ean, Double value, Double saleFee) {
		this.type = type;
		this.description = description;
		this.ean = ean;
		this.value = value;
		this.saleFee = saleFee;
	};

	// Methods

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Storage> getStorages() {
		return storages;
	}

};
