package com.min.task.exception.handler;

import com.min.task.exception.BadRequestException;
import com.min.task.exception.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleBadRequestException(BadRequestException ex) {
        log.info("BadRequestException: {}", ex.getMessage());
        var errorDto = new ErrorDto(BAD_REQUEST, ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(errorDto);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleRunTimeException(RuntimeException ex) {
        log.warn("RuntimeException", ex);
        var errorDto = new ErrorDto(INTERNAL_SERVER_ERROR, ex.getMessage());

        return ResponseEntity
                .internalServerError()
                .body(errorDto);
    }
}
