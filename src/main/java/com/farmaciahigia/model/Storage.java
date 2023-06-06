package com.farmaciahigia.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(columnDefinition = "INTEGER(11) UNSIGNED")
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToMany()
    // @ManyToMany(mappedBy = "product", cascade = CascadeType.ALL)
    // @PrimaryKeyJoinColumn
    private List<Product> products;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    // @MapsId
    @JoinColumn(name = "establishment_id", referencedColumnName = "id")
    private Establishment establishment;

}
