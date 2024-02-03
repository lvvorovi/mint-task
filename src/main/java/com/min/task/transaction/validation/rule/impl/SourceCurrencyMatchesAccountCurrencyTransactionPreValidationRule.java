package com.min.task.transaction.validation.rule.impl;

import com.min.task.account.repository.AccountRepository;
import com.min.task.exception.CurrencyMismatchException;
import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.validation.rule.TransactionRequestPreValidationRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
@Slf4j
public class SourceCurrencyMatchesAccountCurrencyTransactionPreValidationRule implements TransactionRequestPreValidationRule {

    private final AccountRepository accountRepository;


    @Override
    public void validate(TransactionRequest request) {
        accountRepository.findDtoById(request.sourceAccountId())
                .ifPresent(account -> {
                            if (!account.currency().equalsIgnoreCase(request.sourceCurrency())) {
                                log.info("Source currency does not match source account currency");
                                throw new CurrencyMismatchException(
                                        "Source currency does not match source account currency");
                            }
                        }

                );

    }
}
