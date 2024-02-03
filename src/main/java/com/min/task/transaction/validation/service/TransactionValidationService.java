package com.min.task.transaction.validation.service;

import com.min.task.transaction.dto.TransactionRequest;

public interface TransactionValidationService {

    void validate(TransactionRequest transactionRequest);

}
