package com.min.task.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(

        @NotBlank
        @Size(min = 36, max = 36)
        String id,

        @NotBlank
        @Size(max = 10)
        String name
) {
}
