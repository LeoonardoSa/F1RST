package br.com.f1rst.challenge.f1rstbank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.f1rst.challenge.f1rstbank.controller.form.InternetBankAccountForm;
import br.com.f1rst.challenge.f1rstbank.service.InternetAccountService;

@RestController
public class InternetBankAccessController {

    private InternetAccountService service;

    public InternetBankAccessController(InternetAccountService service) {
        this.service = service;
    }

    @PostMapping("internet-bank")
    public ResponseEntity<?> createAccount(@RequestBody InternetBankAccountForm form) {

        service.createUser(form);

        return ResponseEntity.accepted().build();

    }
}
