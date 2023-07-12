package com.farmaciahigia.schemas.product;

import com.farmaciahigia.schemas.ISchema;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductCore implements ISchema {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)

    private String type;

    private String description;

    private String ean;

    private Double value;

    private Double saleFee; // desconto em % (0 ~ 100)

    // methods

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getEan() {
        return ean;
    }

    public Double getValue() {
        return value;
    }

    public Double getSaleFee() {
        return saleFee;
    }

}
