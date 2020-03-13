package com.ftn.backend.controller;

import com.ftn.backend.dto.NewWorkDto;
import com.ftn.backend.model.Work;
import com.ftn.backend.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/work", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkController {

    @Autowired
    private WorkService workService;

    @PostMapping(value = "/new")
    public ResponseEntity<Work> newWork(@RequestBody NewWorkDto newWorkDto) {

        Work work = null;
        try {
            work = this.workService.newWork(newWorkDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(work, HttpStatus.OK);
    }
}
