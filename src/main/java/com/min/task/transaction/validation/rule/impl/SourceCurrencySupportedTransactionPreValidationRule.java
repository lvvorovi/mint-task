package com.min.task.transaction.validation.rule.impl;

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

    @Override
    public void validate(TransactionRequest request) {
        if (!CurrencyManager.getCurrencyList().contains(request.sourceCurrency())) {
            log.info("Requested currency:{} is not supported", request.sourceCurrency());
            throw new CurrencyNotSupportedException("Requested currency:%s is no longer supported"
                    .formatted(request.sourceCurrency()));
        }
    }
}
