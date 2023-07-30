package com.farmaciahigia.schemas.product;

import java.util.ArrayList;
import java.util.List;

import com.farmaciahigia.schemas.ISchema;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class ProductCore implements ISchema {

    private String type = "medicamento";

    private String description = "some product";

    private String ean = "123123131241234";

    private Double value = 100.0;

    private Double saleFee = 0.0; // desconto em % (0 ~ 100)

    protected List<String> errors = new ArrayList<>();

    // methods

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public String getEan() {
        return this.ean;
    }

    public Double getValue() {
        return this.value;
    }

    public Double getSaleFee() {
        return this.saleFee;
    }

    public List<String> errors() {
        return this.errors;
    }

}
