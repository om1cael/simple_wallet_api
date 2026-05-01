package com.om1cael.simple.wallet.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDTO(@NotNull Long receiverId, @NotNull BigDecimal value) {
}
