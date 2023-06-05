package com.farmaciahigia.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String cnpj;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Employee employees;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @Column(nullable = false)
    private Address address;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(referencedColumnName = "id")
    // private Storage storages;
    
}