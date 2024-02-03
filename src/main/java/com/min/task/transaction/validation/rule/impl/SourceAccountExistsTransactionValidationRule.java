package com.min.task.transaction.validation.rule.impl;

import com.min.task.account.repository.AccountRepository;
import com.min.task.exception.AccountIdDoesNotExistException;
import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.validation.rule.TransactionRequestValidationRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(0)
@Slf4j
public class SourceAccountExistsTransactionValidationRule implements TransactionRequestValidationRule {

    private final AccountRepository accountRepository;


    @Override
    public void validate(TransactionRequest request) {
        if (!accountRepository.existsById(request.sourceAccountId())) {
            log.info("Account does not exist with id:{}", request.sourceAccountId());
            throw new AccountIdDoesNotExistException(
                    "Account does not exist with id:%s"
                            .formatted(request.sourceAccountId()));
        }
    }
}
