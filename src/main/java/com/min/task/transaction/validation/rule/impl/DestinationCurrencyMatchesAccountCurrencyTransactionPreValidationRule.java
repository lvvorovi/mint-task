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
public class DestinationCurrencyMatchesAccountCurrencyTransactionPreValidationRule implements TransactionRequestPreValidationRule {

    private final AccountRepository accountRepository;


    @Override
    public void validate(TransactionRequest request) {
        accountRepository.findDtoById(request.destinationAccountId())
                .ifPresent(account -> {
                            if (!account.currency().equalsIgnoreCase(request.destinationCurrency())) {
                                log.info("Destination currency does not match source account currency");
                                throw new CurrencyMismatchException(
                                        "Destination currency does not match source account currency");
                            }
                        }
                );

    }
}
