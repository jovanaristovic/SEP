package com.ftn.paypal.dto;

import lombok.Data;

@Data
public class BuyJournalDto {

    private Long productId;
    private String productName;
    private String userEmail;
    private double price;
    private Long purchaseId;

    public BuyJournalDto() {}

    public BuyJournalDto(Long productId, String productName, String userEmail, double price, Long purchaseId) {
        this.productId = productId;
        this.productName = productName;
        this.userEmail = userEmail;
        this.price = price;
        this.purchaseId = purchaseId;
    }
}
