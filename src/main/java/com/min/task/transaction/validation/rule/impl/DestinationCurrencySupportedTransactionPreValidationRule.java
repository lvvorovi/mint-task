package com.min.task.transaction.validation.rule.impl;

import com.min.task.currency.CurrencyManager;
import com.min.task.exception.NotSupportedCurrencyException;
import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.validation.rule.TransactionRequestPreValidationRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j
public class DestinationCurrencySupportedTransactionPreValidationRule implements TransactionRequestPreValidationRule {


    @Override
    public void validate(TransactionRequest request) {
        if (!CurrencyManager.getCurrencyList().contains(request.destinationCurrency())) {
            log.info("Requested currency:{} is not supported", request.destinationCurrency());
            throw new NotSupportedCurrencyException("Requested currency:%s is no longer supported"
                    .formatted(request.destinationCurrency()));
        }
    }

}
