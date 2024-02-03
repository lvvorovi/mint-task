package com.min.task.transaction.validation.rule.impl;

import com.min.task.exception.BadRequestException;
import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.validation.rule.TransactionRequestValidationRule;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class SourceAndDestinationAccountsAreDifferentTransactionValidationRule implements TransactionRequestValidationRule {

    @Override
    public void validate(TransactionRequest request) {
        if (request.sourceAccountId().equals(request.destinationAccountId())) {
            throw new BadRequestException("Transfers within the same accounts are not allowed");
        }
    }
}
