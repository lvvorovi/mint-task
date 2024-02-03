package com.min.task.account.dto;

import com.min.task.user.dto.UserResponse;

import java.math.BigDecimal;


public record AccountResponse(
        String id,
        UserResponse userResponse,
        String currency,
        BigDecimal balance
) {
}
