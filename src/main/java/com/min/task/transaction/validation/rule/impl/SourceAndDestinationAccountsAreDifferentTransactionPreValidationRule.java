package com.min.task.transaction.validation.rule.impl;

import com.min.task.exception.BadRequestException;
import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.validation.rule.TransactionRequestPreValidationRule;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class SourceAndDestinationAccountsAreDifferentTransactionPreValidationRule implements TransactionRequestPreValidationRule {

    @Override
    public void validate(TransactionRequest request) {
        if (request.sourceAccountId().equals(request.destinationAccountId())) {
            throw new BadRequestException("Transfers within the same accounts are not allowed");
        }
    }
}
