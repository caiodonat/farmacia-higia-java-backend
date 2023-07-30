package com.farmaciahigia.schemas.customer;

import java.util.Date;

import com.farmaciahigia.schemas.ISchema;

import io.swagger.v3.oas.annotations.media.Schema;

public class CustomerCore implements ISchema {
    
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String cpf;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private Date birthDate;

    // methods

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
