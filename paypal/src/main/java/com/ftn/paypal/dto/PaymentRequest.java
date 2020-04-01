package com.ftn.paypal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class PaymentRequest {

    private Double priceAmount;
    private String priceCurrency;
    private String description;
    private String hashedOrderId;

}
