package com.ftn.backend.service;

import com.ftn.backend.model.ScientificField;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScientificFieldService {

    List<ScientificField> findAll();
    ScientificField findByName(String name);
}
