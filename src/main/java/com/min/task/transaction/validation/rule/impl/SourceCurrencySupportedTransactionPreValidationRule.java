package com.min.task.transaction.validation.rule.impl;

import com.min.task.account.repository.AccountRepository;
import com.min.task.currency.CurrencyManager;
import com.min.task.exception.CurrencyNotSupportedException;
import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.validation.rule.TransactionRequestPreValidationRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
@Slf4j
@RequiredArgsConstructor
public class SourceCurrencySupportedTransactionPreValidationRule implements TransactionRequestPreValidationRule {

    private final AccountRepository accountRepository;

    @Override
    public void validate(TransactionRequest request) {
        accountRepository.findDtoById(request.sourceAccountId())
                .ifPresent(account -> {
                    if (!CurrencyManager.getCurrencyList().contains(account.currency())) {
                        log.info("Requested currency:{} is not supported", account.currency());
                        throw new CurrencyNotSupportedException("Requested currency:%s is no longer supported"
                                .formatted(account.currency()));
                    }
                });

    }
}
