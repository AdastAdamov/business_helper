package com.adast_adamov.business_helper.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "BANK_NAME")
    private String bankIssuer;

    @Column(name = "PACKAGE_NAME")
    private String tariffName;

}
