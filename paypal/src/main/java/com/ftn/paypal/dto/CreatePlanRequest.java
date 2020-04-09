package com.ftn.paypal.dto;

import com.ftn.paypal.enumeration.FrequencyPayment;
import com.ftn.paypal.enumeration.PaymentTypePlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@RequiredArgsConstructor
public class CreatePlanRequest {
    private Double price;
    private String currency;
    private String titleOfJournal;
    private String description;
    private PaymentTypePlan typeOfPlan;
    private FrequencyPayment frequencyPayment;
    private int frequencyInterval;
    private int cycles;
}
