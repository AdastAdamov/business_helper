package com.adast_adamov.business_helper.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "user_products")
public class UserProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "last_operation_date")
    private LocalDate lastOperationDate;

}
