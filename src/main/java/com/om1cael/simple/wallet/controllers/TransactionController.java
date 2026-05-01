package com.om1cael.simple.wallet.controllers;

import com.om1cael.simple.wallet.dtos.TransactionDTO;
import com.om1cael.simple.wallet.dtos.UserResponseDTO;
import com.om1cael.simple.wallet.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @PostMapping
    private ResponseEntity<UserResponseDTO> transfer(@RequestBody @Valid TransactionDTO transactionDTO) {
        return new ResponseEntity<>(service.transfer(transactionDTO), HttpStatus.OK);
    }
}
