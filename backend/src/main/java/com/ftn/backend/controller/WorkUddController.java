package com.ftn.backend.controller;

import com.ftn.backend.elasticsearch.dto.NewWorkUddDto;
import com.ftn.backend.elasticsearch.WorkElasticsearch;
import com.ftn.backend.service.WorkUddService;
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
@RequestMapping(value = "/api/work/udd", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkUddController {


    @Autowired
    private WorkUddService workUddService;

    @PostMapping(value = "/new")
    public ResponseEntity<WorkElasticsearch> newWork(@RequestBody NewWorkUddDto newWorkDto) {

        WorkElasticsearch work = null;
        try {
            work = this.workUddService.newWorkUdd(newWorkDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(work, HttpStatus.OK);
    }
}
