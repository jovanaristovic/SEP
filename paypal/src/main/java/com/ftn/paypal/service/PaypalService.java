package com.ftn.paypal.service;

import com.ftn.paypal.dto.CreatePlanRequest;
import com.ftn.paypal.model.JournalPlan;
import com.ftn.paypal.repository.JournalPlanRepository;
import com.paypal.api.payments.*;
import com.paypal.api.payments.Currency;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaypalService {

    @Autowired
    private APIContext apiContext;

    @Autowired
    private JournalPlanRepository journalPlanRepository;

    public Payment createPayment(Double total, String currency,
                                String cancelUrl, String successUrl) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.valueOf(total));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        Payment payment = new Payment();
        payment.setPayer(payer);
        payment.setIntent("sale");
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

    public Long createPlanForSubscription(CreatePlanRequest createPlanRequest) {
        //Build plan object
        Plan plan = new Plan();
        plan.setName(createPlanRequest.getTitleOfJournal());
        plan.setDescription(createPlanRequest.getDescription());
        plan.setType("INFINITE");

        //payment definitions
        PaymentDefinition paymentDefinition = new PaymentDefinition();
        paymentDefinition.setName(String.format("%s payments", createPlanRequest.getTypeOfPlan().name()));
        paymentDefinition.setType(createPlanRequest.getTypeOfPlan().name());
        paymentDefinition.setFrequency(createPlanRequest.getFrequencyPayment().name());
        paymentDefinition.setFrequencyInterval(String.valueOf(createPlanRequest.getFrequencyInterval()));
        paymentDefinition.setCycles(String.valueOf(createPlanRequest.getCycles()));

        //currency
        Currency currency = new Currency();
        currency.setCurrency(createPlanRequest.getCurrency());
        currency.setValue(String.valueOf(createPlanRequest.getPrice()));
        paymentDefinition.setAmount(currency);

        //payment definition
        List<PaymentDefinition> paymentDefinitionList = new ArrayList<>();
        paymentDefinitionList.add(paymentDefinition);
        plan.setPaymentDefinitions(paymentDefinitionList);

        //merchant preferences
        MerchantPreferences merchantPreferences = new MerchantPreferences();
        merchantPreferences.setSetupFee(currency);
        merchantPreferences.setCancelUrl("http://localhost:4200");
        merchantPreferences.setReturnUrl("https://localhost:8090/api/paypal/plan/finishSubscription/");
        merchantPreferences.setMaxFailAttempts("0");
        merchantPreferences.setAutoBillAmount("YES");
        merchantPreferences.setInitialFailAmountAction("CONTINUE");
        plan.setMerchantPreferences(merchantPreferences);

        return activatePlan(plan, createPlanRequest.getTitleOfJournal());
    }

    private Long activatePlan(Plan plan, String titleOfJournal) {
        try {
            Plan createdPlan = plan.create(apiContext);

            // Set up plan activate PATCH request
            List<Patch> patchRequestList = new ArrayList<>();
            Map<String, String> value = new HashMap<>();
            value.put("state", "ACTIVE");

            // Create update object to activate plan
            Patch patch = new Patch();
            patch.setPath("/");
            patch.setValue(value);
            patch.setOp("replace");
            patchRequestList.add(patch);

            // Activate plan
            createdPlan.update(apiContext, patchRequestList);
            JournalPlan journalPlan;
            journalPlan = JournalPlan.builder().journal(titleOfJournal).planId(createdPlan.getId()).build();
            JournalPlan journalPlan1 = journalPlanRepository.save(journalPlan);
            return journalPlan1.getId();

        } catch (PayPalRESTException e) {
           System.out.print(e);
        }
        return 0L;
    }


    public String subscribeToPlan(CreatePlanRequest request) {

        Long journalId = createPlanForSubscription(request);

        Agreement agreement = new Agreement();
        agreement.setName(String.format("Subscription for magazin no %s", journalId));
        agreement.setDescription("Basic Agreement");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        // Add 30 seconds to make sure Paypal accept the agreement date
        Date rightNow = new Date(new Date().getTime() + 30000);
        agreement.setStartDate(df.format(rightNow));

        Plan plan = new Plan();
        JournalPlan journalPlan = journalPlanRepository.findJournalPlanById(journalId);
        plan.setId(journalPlan.getPlanId());
        agreement.setPlan(plan);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        agreement.setPayer(payer);

        try {
            agreement = agreement.create(apiContext);
            for (Links links : agreement.getLinks()) {
                if ("approval_url".equals(links.getRel())) {
                    return links.getHref();
                    //REDIRECT USER TO url
                }
            }
        } catch (UnsupportedEncodingException | PayPalRESTException | MalformedURLException e) {
            System.out.print(e);
        }
        return null;
    }


    public void finishSubscription(String token) {
        Agreement agreement = new Agreement();
        agreement.setToken(token);
        try {
            Agreement activeAgreement = agreement.execute(apiContext, agreement.getToken());
        } catch (PayPalRESTException e) {
            System.out.print(e);
        }
    }

}
