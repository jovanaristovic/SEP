package com.ftn.backend.model;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity(name = "TRANSACTIONS")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column
    private String productName;

    @Column
    private Long productId;

    @Column
    private String buyerEmail;

    @Column
    private double amount;

    @Column
    private String status;

    @Column
    private Long purchaseId;

    public Transaction( ) {

    }

    public Transaction(String productName, Long productId, String buyerEmail, double amount, String status, Long purchaseId) {
        this.productName = productName;
        this.productId = productId;
        this.buyerEmail = buyerEmail;
        this.amount = amount;
        this.status = status;
        this.purchaseId = purchaseId;
    }
}
