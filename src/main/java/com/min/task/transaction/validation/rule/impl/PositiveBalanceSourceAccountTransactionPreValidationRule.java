package com.min.task.transaction.validation.rule.impl;

import com.min.task.account.repository.AccountRepository;
import com.min.task.exception.NotEnoughAccountBalanceException;
import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.validation.rule.TransactionRequestPostValidationRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(0)
@RequiredArgsConstructor
@Slf4j
public class PositiveBalanceSourceAccountTransactionPreValidationRule implements TransactionRequestPostValidationRule {


    private final AccountRepository accountRepository;

    @Override
    public void validate(TransactionRequest request, BigDecimal sourceAmount) {
        accountRepository.findDtoById(request.sourceAccountId())
                .ifPresent(account -> {
                    if (account.balance().compareTo(sourceAmount) < 0) {
                        log.info("Transaction refused. " +
                                        "Current balance of accountId:" +
                                        "{} is: {}, while requested:{}",
                                request.sourceAccountId(),
                                account.balance(),
                                sourceAmount);

                        throw new NotEnoughAccountBalanceException(
                                "Not Enough balance on account with id: %s"
                                        .formatted(request.sourceAccountId()));
                    }
                });
    }
}
