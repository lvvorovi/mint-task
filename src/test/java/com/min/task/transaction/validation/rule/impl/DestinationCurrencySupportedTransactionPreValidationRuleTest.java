package com.min.task.transaction.validation.rule.impl;

import com.min.task.currency.CurrencyManager;
import com.min.task.exception.CurrencyNotSupportedException;
import com.min.task.transaction.dto.TransactionRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DestinationCurrencySupportedTransactionPreValidationRuleTest {

    @InjectMocks
    DestinationCurrencySupportedTransactionPreValidationRule victim;

    @Test
    void validate_whenDoesNotContain_thenThrowCurrencyNotSupportedException() {
        var transactionRequestMock = mock(TransactionRequest.class);
        when(transactionRequestMock.destinationCurrency()).thenReturn("EUR");
        var currencyList = new ArrayList<String>();
        currencyList.add("USD");
        try (MockedStatic<CurrencyManager> utilities = mockStatic(CurrencyManager.class)) {
            utilities.when(CurrencyManager::getCurrencyList).thenReturn(currencyList);
            assertThatThrownBy(() -> victim.validate(transactionRequestMock))
                    .isInstanceOf(CurrencyNotSupportedException.class)
                    .hasMessageContaining("no longer supported");
        }
    }

    @Test
    void validate_whenContains_thenDoNothing() {
        var transactionRequestMock = mock(TransactionRequest.class);
        when(transactionRequestMock.destinationCurrency()).thenReturn("EUR");
        var currencyList = new ArrayList<String>();
        currencyList.add("EUR");
        try (MockedStatic<CurrencyManager> utilities = mockStatic(CurrencyManager.class)) {
            utilities.when(CurrencyManager::getCurrencyList).thenReturn(currencyList);
            assertThatNoException().isThrownBy(() -> victim.validate(transactionRequestMock));
        }
    }


}
