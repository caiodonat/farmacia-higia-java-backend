package com.farmaciahigia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 11)
    private Integer cpf;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Integer phone;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive = true;

    @Column(columnDefinition = "boolean default false")
    private Boolean isManager = false;

    @ManyToOne()
    private Establishment establishment;
}
