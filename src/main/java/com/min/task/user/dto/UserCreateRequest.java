package com.min.task.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserCreateRequest(

        @NotBlank
        @Size(max = 10)
        String name

) {
}
