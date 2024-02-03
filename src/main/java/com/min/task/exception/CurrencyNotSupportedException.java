package com.min.task.exception;

public class CurrencyNotSupportedException extends BadRequestException {

    public CurrencyNotSupportedException(String message) {
        super(message);
    }

}
