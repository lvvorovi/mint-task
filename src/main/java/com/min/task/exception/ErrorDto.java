package com.min.task.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorDto(
        String status,
        String error,
        String message,
        LocalDateTime timestamp
) {

    public ErrorDto(
            HttpStatus status,
            String message
    ) {
        this(
                String.valueOf(status.value()),
                status.getReasonPhrase(),
                message,
                LocalDateTime.now());
    }
}
