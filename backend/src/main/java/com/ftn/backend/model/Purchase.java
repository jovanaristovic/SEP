package com.ftn.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Purchase {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long productId;

    @Column
    private String status;

    @Column
    private String typeOfProduct;

    public Purchase() {}

    public Purchase(Long productId, String status, String typeOfProduct ) {
        this.productId = productId;
        this.status = status;
        this.typeOfProduct = typeOfProduct;
    }
}
