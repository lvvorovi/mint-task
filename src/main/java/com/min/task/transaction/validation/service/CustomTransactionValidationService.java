package com.min.task.transaction.validation.service;

import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.validation.rule.TransactionRequestPostValidationRule;
import com.min.task.transaction.validation.rule.TransactionRequestPreValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomTransactionValidationService implements TransactionValidationService {

    private final List<TransactionRequestPreValidationRule> preValidationRules;
    private final List<TransactionRequestPostValidationRule> postValidationRules;

    @Override
    public void preValidate(TransactionRequest transactionRequest) {
        preValidationRules.forEach(rule -> rule.validate(transactionRequest));
    }

    @Override
    public void postValidated(TransactionRequest transactionRequest, BigDecimal sourceAmount) {
        postValidationRules.forEach(rule -> rule.validate(transactionRequest, sourceAmount));
    }
}
