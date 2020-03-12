package com.ftn.backend.controller;


import com.ftn.backend.model.ScientificField;
import com.ftn.backend.service.ScientificFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/scientificField", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScientificFieldController {

    @Autowired
    private ScientificFieldService scientificFieldService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ScientificField>> getAllScinftificFields() {
        List<ScientificField> scientificFields = this.scientificFieldService.findAll();
        return new ResponseEntity<>(scientificFields, HttpStatus.OK);
    }
}
