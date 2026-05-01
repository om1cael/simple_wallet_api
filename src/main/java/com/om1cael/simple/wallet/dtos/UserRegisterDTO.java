package com.om1cael.simple.wallet.dtos;

public record UserRegisterDTO(
        String fullName,
        String document,
        String email,
        String password
) {
}
