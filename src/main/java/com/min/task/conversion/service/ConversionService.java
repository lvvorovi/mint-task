package com.min.task.conversion.service;

import com.min.task.transaction.dto.TransactionRequest;

import java.math.BigDecimal;

public interface ConversionService {
    BigDecimal convert(TransactionRequest transactionRequest);
}
