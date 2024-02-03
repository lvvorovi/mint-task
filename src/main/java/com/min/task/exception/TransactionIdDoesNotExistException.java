package com.min.task.exception;

public class TransactionIdDoesNotExistException extends BadRequestException {

    public TransactionIdDoesNotExistException(String message) {
        super(message);
    }

}
