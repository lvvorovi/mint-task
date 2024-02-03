package com.min.task.exception;

public class UserNameAlreadyRegisteredException extends BadRequestException {

    public UserNameAlreadyRegisteredException(String message) {
        super(message);
    }
}
