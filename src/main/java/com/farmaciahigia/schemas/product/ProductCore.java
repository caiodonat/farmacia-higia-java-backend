package com.farmaciahigia.schemas.product;

import java.util.ArrayList;
import java.util.List;

import com.farmaciahigia.schemas.ISchema;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class ProductCore implements ISchema {

	private String type;

	private String description;

	private String ean;

	private Double value;

	private Double saleFee; // desconto em % (0 ~ 100)
	
	private String imgUrl;

	protected List<String> errors = new ArrayList<>();

	// methods

	public ProductCore() {
	}

	@Override
	public String toString() {
		return "ProductCore [type=" + type + ", description=" + description + ", ean=" + ean + ", value=" + value
				+ ", saleFee=" + saleFee + ", errors=" + errors + "]";
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<String> errors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
