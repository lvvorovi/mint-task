package com.min.task.exception;

public class AccountIdDoesNotExistException extends BadRequestException {

    public AccountIdDoesNotExistException(String message) {
        super(message);
    }

}
