package com.min.task.exception;

public class CurrencyMismatchException extends BadRequestException {

    public CurrencyMismatchException(String message) {
        super(message);
    }

}
