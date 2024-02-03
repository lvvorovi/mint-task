package com.min.task.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.min.task.user.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record AccountCreateRequest(

        @NotNull
        @JsonProperty("user")
        UserDto userDto,

        @NotNull
        @JsonProperty("currency")
        String currency
) {
}
