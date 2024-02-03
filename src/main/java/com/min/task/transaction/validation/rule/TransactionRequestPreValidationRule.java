package com.min.task.transaction.validation.rule;

import com.min.task.transaction.dto.TransactionRequest;

public interface TransactionRequestPreValidationRule {

    void validate(TransactionRequest request);

}
