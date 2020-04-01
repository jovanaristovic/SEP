package com.ftn.backend.controller;

import com.ftn.backend.dto.BuyJournalDto;
import com.ftn.backend.dto.NewJournalDto;
import com.ftn.backend.model.Journal;
import com.ftn.backend.model.JournalPurchase;
import com.ftn.backend.service.JournalPurchaseService;
import com.ftn.backend.service.JournalService;
import com.ftn.backend.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/journal", produces = MediaType.APPLICATION_JSON_VALUE)
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private JournalPurchaseService journalPurchaseService;

    private static final String PAYPAL_URI= "http://localhost:8090/api/paypal";


    @PostMapping(value = "/new")
    public ResponseEntity<Journal> newJournal(@RequestBody NewJournalDto newJournalDto) {

        Journal journal = this.journalService.newJournal(newJournalDto);
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Journal>> getAllJournals() {
        List<Journal> journals = this.journalService.findAllJournals();
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable Long id) {
        Journal journal = this.journalService.findJournalById(id);
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

    @GetMapping(value = "/buy/{journalId}")
    public  Map<String, String> buyJournal(@PathVariable Long journalId, @RequestHeader(value = "Authorization") String authorization){

        String email = UtilityService.getEmailFromToken(authorization);
        if (email == null) {
            try {
                throw new Exception("Jwt error");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BuyJournalDto buyJournalDto = this.journalService.buyJournal(journalId,email);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity requestEntity = new HttpEntity<>(buyJournalDto, requestHeaders);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> resp = restTemplate.postForEntity(PAYPAL_URI + "/pay",requestEntity, String.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("url", resp.getBody());

        return map;
    }


    @GetMapping(value = "/complete/{id}")
    public void successOrder(@PathVariable Long id){
        this.journalPurchaseService.changeStatusPaid(id);

    }

    @GetMapping(value = "/cancel/{id}")
    public void cancelorder(@PathVariable Long id){
        this.journalPurchaseService.changeStatusPaid(id);
    }


}
