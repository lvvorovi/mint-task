package com.min.task.exception;

public class NotEnoughAccountBalanceException extends BadRequestException {

    public NotEnoughAccountBalanceException(String message) {
        super(message);
    }

}
