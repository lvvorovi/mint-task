package com.min.task.currency.conversion.service;

import com.min.task.transaction.dto.TransactionRequest;

import java.math.BigDecimal;

public interface ConversionService {
    BigDecimal convertToSourceAmount(TransactionRequest transactionRequest);
}
