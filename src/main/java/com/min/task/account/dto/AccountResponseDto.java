package com.min.task.account.dto;

import com.min.task.user.dto.UserResponseDto;

import java.math.BigDecimal;


public record AccountResponseDto(
        String id,
        UserResponseDto userResponseDto,
        String currency,
        BigDecimal balance
) {
}
