package com.min.task.acccount.dto;

import com.min.task.cons.Currency;
import com.min.task.user.dto.UserResponseDto;

import java.math.BigDecimal;


public record AccountResponseDto(
        String id,
        UserResponseDto userResponseDto,
        Currency currency,
        BigDecimal balance
) {
}
