package com.farmaciahigia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // @Column(length = 2)
    private String uf;

    private String country;

    private String street;

    private Integer number;

    private String observation;

    @OneToOne(mappedBy = "address")
    private Establishment establishment;

    public Address() {
    }

    public Address(Long id, String uf, String country, String street, Integer number, String observation) {
        this.id = id;
        this.uf = uf;
        this.country = country;
        this.street = street;
        this.number = number;
        this.observation = observation;
    }

    @Override
    public String toString() {
        return "Address [id=" + id + ", uf=" + uf + ", country=" + country + ", street=" + street + ", number=" + number
                + ", observation=" + observation + "]";
    }

}
