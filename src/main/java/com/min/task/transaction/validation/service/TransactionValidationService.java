package com.min.task.transaction.validation.service;

import com.min.task.transaction.dto.TransactionRequest;

import java.math.BigDecimal;

public interface TransactionValidationService {

    void preValidate(TransactionRequest transactionRequest);

    void postValidated(TransactionRequest transactionRequest, BigDecimal sourceAmount);
}
