package com.farmaciahigia.model;

import java.util.List;

import com.farmaciahigia.schemas.product.ProductCore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity(name = "product")
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
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
		setDescription(product.getDescription());
		setType(product.getType());
		setEan(product.getEan());
		setValue(product.getValue());
		setSaleFee(product.getSaleFee());
	};
	
	public Product(Product product) {
		setId(product.getId());
		setType(product.getType());
		setDescription(product.getDescription());
		setEan(product.getEan());
		setValue(product.getValue());
		setSaleFee(product.getSaleFee());
	};
	
	public Product(String type, String description, String ean, Double value, Double saleFee) {
		setType(type);
		setDescription(description);
		setEan(ean);
		setValue(value);
		setSaleFee(saleFee);
	};
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getSaleFee() {
		return saleFee;
	}

	public void setSaleFee(Double saleFee) {
		this.saleFee = saleFee;
	}

	public List<Storage> getStorages() {
		return storages;
	}

	public void setStorages(List<Storage> storages) {
		this.storages = storages;
	}
	
};
