package com.farmaciahigia.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(columnDefinition = "INTEGER(11) UNSIGNED")
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Product products;

    private Integer quantity;

    @ManyToOne()
    private Establishment establishment;

}
