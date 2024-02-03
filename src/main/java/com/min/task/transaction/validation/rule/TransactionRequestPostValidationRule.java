package com.min.task.transaction.validation.rule;

import com.min.task.transaction.dto.TransactionRequest;

import java.math.BigDecimal;

public interface TransactionRequestPostValidationRule {

    void validate(TransactionRequest request, BigDecimal sourceAmount);

}
