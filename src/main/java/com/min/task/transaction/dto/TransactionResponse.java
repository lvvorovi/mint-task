package com.min.task.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        String id,
        String sourceAccountId,
        String destinationAccountId,
        BigDecimal sourceAmount,
        BigDecimal destinationAmount,
        LocalDateTime created
) {
}
