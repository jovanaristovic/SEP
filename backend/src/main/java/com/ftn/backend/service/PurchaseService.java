package com.ftn.backend.service;

import com.ftn.backend.model.Purchase;
import org.springframework.stereotype.Service;

@Service
public interface PurchaseService {

    Purchase save (Purchase purchase);
    void changeStatusPaid(Long id);
    void changeStatusCanceled(Long id);
}
