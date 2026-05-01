package com.om1cael.simple.wallet.controllers;

import com.om1cael.simple.wallet.dtos.ExceptionDTO;
import com.om1cael.simple.wallet.exceptions.MoneyTransferException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ExceptionDTO> entityNotFound(EntityNotFoundException e) {
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MoneyTransferException.class)
    private ResponseEntity<ExceptionDTO> moneyTransferException(MoneyTransferException e) {
        return new ResponseEntity<>(new ExceptionDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
