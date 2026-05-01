package com.om1cael.simple.wallet.dtos;

import java.math.BigDecimal;

public record UserResponseDTO(
        Long id,
        BigDecimal balance
) {
}
