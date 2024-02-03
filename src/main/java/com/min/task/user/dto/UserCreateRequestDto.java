package com.min.task.user.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Valid
public record UserCreateRequestDto(

        @NotBlank
        @Size(max = 10)
        String name

) {
}
