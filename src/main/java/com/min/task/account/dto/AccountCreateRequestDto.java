package com.min.task.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.min.task.cons.Currency;
import com.min.task.user.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record AccountCreateRequestDto(

        @NotNull
        @JsonProperty("user")
        UserDto userDto,

        @NotNull
        @JsonProperty("currency")
        Currency currency
) {
}
