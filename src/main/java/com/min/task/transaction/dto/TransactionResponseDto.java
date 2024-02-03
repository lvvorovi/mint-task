package com.min.task.transaction.dto;

import com.min.task.cons.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponseDto(
        String id,
        String accountId,
        Currency currency,
        BigDecimal amount,
        LocalDateTime created
) {
}
