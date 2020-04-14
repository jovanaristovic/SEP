package com.ftn.backend.dto;

import lombok.Data;

@Data
public class PurchaseDto {

    private String status;
    private String typeOfProduct;
    private String title;
    private Long price;

    public PurchaseDto() {}

    public PurchaseDto(String status, String typeOfProduct, String title, Long price) {
        this.status = status;
        this.title = title;
        this.typeOfProduct = typeOfProduct;
        this.price = price;
    }
}
