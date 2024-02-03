package com.min.task.exception;

public class UserDoesNotExistException extends BadRequestException {

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
