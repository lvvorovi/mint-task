package com.min.task.transaction.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TransactionRequest(

        @NotBlank
        @Size(min = 36, max = 36)
        String sourceAccountId,

        @NotBlank
        @Size(min = 36, max = 36)
        String destinationAccountId,
        @NotBlank
        @Size(max = 10)
        String sourceCurrency,

        @NotBlank
        @Size(max = 10)
        String destinationCurrency,

        @Positive
        @Digits(integer = 10, fraction = 2)
        BigDecimal amount

) {
}
