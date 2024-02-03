package com.min.task.transaction.validation.rule.impl;

import com.min.task.account.repository.AccountRepository;
import com.min.task.exception.NotEnoughAccountBalanceException;
import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.validation.rule.TransactionRequestValidationRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
@RequiredArgsConstructor
@Slf4j
public class PositiveBalanceSourceAccountTransactionValidationRule implements TransactionRequestValidationRule {

    private final AccountRepository accountRepository;

    @Override
    public void validate(TransactionRequest request) {
        accountRepository.findDtoById(request.sourceAccountId())
                .ifPresent(account -> {
                    if (account.balance().compareTo(request.amount()) < 0) {
                        log.info("Transaction refused. " +
                                        "Current balance of accountId:" +
                                        "{} is: {} {}, while requested:{} {}",
                                account.id(),
                                account.balance(),
                                account.currency(),
                                request.amount(),
                                request.currencyFrom());

                        throw new NotEnoughAccountBalanceException(
                                "Not Enough balance on account with id: %s"
                                        .formatted(request.sourceAccountId()));
                    }
                });
    }
}
