package com.om1cael.simple.wallet.controllers;

import com.om1cael.simple.wallet.dtos.JwtTokenDto;
import com.om1cael.simple.wallet.dtos.UserRegisterDTO;
import com.om1cael.simple.wallet.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    private ResponseEntity<JwtTokenDto> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        return new ResponseEntity<>(service.register(userRegisterDTO), HttpStatus.CREATED);
    }
}
