package com.min.task.acccount.dto;

import com.min.task.cons.Currency;
import com.min.task.user.dto.UserDto;

import java.math.BigDecimal;


public record AccountResponseDto(
        String id,
        UserDto userDto,
        Currency currency,
        BigDecimal balance
) {
}
