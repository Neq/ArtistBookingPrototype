package com.freitag.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "contract")
public class Contract {
    private @Id @GeneratedValue Long id;

    private String details;

    @OneToOne
    private Offer offer;

    public Contract() {}




}
