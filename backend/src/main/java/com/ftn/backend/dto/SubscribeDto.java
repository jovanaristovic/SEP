package com.ftn.backend.dto;

import com.ftn.backend.model.enumeration.FrequencyPayment;
import com.ftn.backend.model.enumeration.PaymentTypePlan;
import lombok.Data;

@Data
public class SubscribeDto {
    private Double price;
    private String currency;
    private String titleOfJournal;
    private String description;
    private int frequencyInterval;
    private PaymentTypePlan typeOfPlan;
    private FrequencyPayment frequencyPayment;
    private int cycles;
}
