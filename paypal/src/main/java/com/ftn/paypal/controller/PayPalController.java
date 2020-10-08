package com.ftn.paypal.controller;

import com.ftn.paypal.dto.BuyJournalDto;
import com.ftn.paypal.dto.CreatePlanRequest;
import com.paypal.api.payments.Links;
import com.ftn.paypal.service.PaypalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;

@RestController
@RequestMapping(value = "api/paypal")
public class PayPalController {

    @Autowired
    private PaypalService paypalService;

    private static final String SUCCESS_URL = "/pay/complete";
    private static final String CANCEL_URL = "/pay/cancel";

    @PostMapping(value = "/pay")
    public String pay(@RequestBody BuyJournalDto request) {
        String cancelUrl = "";
        String successUrl = "";
        successUrl = "https://localhost:8090/api/paypal" + SUCCESS_URL+"/"+ request.getPurchaseId();
        cancelUrl = "https://localhost:8090/api/paypal" + CANCEL_URL+"/" + request.getPurchaseId();

        try {
            Payment payment = paypalService.createPayment(
                    request.getPrice(),
                   "EUR",
                    cancelUrl,
                    successUrl);



            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            System.out.print(e.getMessage());
        }
    return "";

    }





    @GetMapping(value = "/pay/complete/{id}")
    public RedirectView completePay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @PathVariable Long id){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> resp = restTemplate.getForEntity("http://localhost:8080/api/journal/complete/"+id, String.class);
                return new RedirectView("http://localhost:4200");
            }
        } catch (PayPalRESTException e) {
            System.out.print(e);
        }
        return new RedirectView("http://localhost:4200");

    }

    @GetMapping(value = "/pay/cancel/{id}")
    public RedirectView cancelPay(@PathVariable String id) {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.getForEntity("http://localhost:8080/api/journal/cancel/"+id, String.class);
        return new RedirectView("http://localhost:4200");

    }

    @PostMapping(value = "/plan/create")
    public ResponseEntity createPlanForSubscription(@RequestBody CreatePlanRequest requestCreatePlan) {
        paypalService.createPlanForSubscription(requestCreatePlan);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/plan/subscribe")
    public ResponseEntity subscribeToPlan(@RequestBody CreatePlanRequest subscribeDto) {
        String url = paypalService.subscribeToPlan(subscribeDto);
        return ResponseEntity.ok(url);
    }

    @GetMapping(value = "/plan/finishSubscription")
    public ResponseEntity finishSubscription(@RequestParam("token") String token){
        paypalService.finishSubscription(token);
        return ResponseEntity.ok("Subscription finished! <a href='http://localhost:4200'>Home</a>");
    }


}