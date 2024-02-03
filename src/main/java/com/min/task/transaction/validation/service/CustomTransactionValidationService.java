package com.min.task.transaction.validation.service;

import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.validation.rule.TransactionRequestValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomTransactionValidationService implements TransactionValidationService {

    private final List<TransactionRequestValidationRule> validationRules;

    @Override
    public void validate(TransactionRequest transactionRequest) {
        validationRules.forEach(rule -> rule.validate(transactionRequest));
    }
}
