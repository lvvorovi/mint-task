package com.min.task.exception;

public class NotSupportedCurrencyException extends BadRequestException {

    public NotSupportedCurrencyException(String message) {
        super(message);
    }

}
