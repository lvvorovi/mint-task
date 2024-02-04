package com.min.task.transaction.validation.rule.impl;

import com.min.task.account.dto.AccountResponse;
import com.min.task.account.repository.AccountRepository;
import com.min.task.exception.CurrencyMismatchException;
import com.min.task.transaction.dto.TransactionRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.min.task.test.util.TestUtil.TRANSACTION_ID;
import static com.min.task.test.util.TestUtil.testTransactionRequest;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SourceCurrencyMatchesAccountCurrencyTransactionPreValidationRuleTest {

    @Mock
    AccountRepository repository;

    @InjectMocks
    SourceCurrencyMatchesAccountCurrencyTransactionPreValidationRule victim;

    @Test
    void validate_whenAccountDoesNotExist_thenDoNothing() {
        var transactionRequest = testTransactionRequest();
        when(repository.findDtoById(transactionRequest.sourceAccountId()))
                .thenReturn(Optional.empty());

        assertThatNoException().isThrownBy(() -> victim.validate(transactionRequest));
    }

    @Test
    void validate_whenAccountExists_andCurrenciesMatches_thenDoNothing() {
        var transactionRequest = mock(TransactionRequest.class);
        when(transactionRequest.sourceAccountId()).thenReturn(TRANSACTION_ID);
        var accountResponse = mock(AccountResponse.class);
        when(repository.findDtoById(TRANSACTION_ID)).thenReturn(Optional.of(accountResponse));
        when(accountResponse.currency()).thenReturn("EUR");
        when(transactionRequest.sourceCurrency()).thenReturn("EUR");

        assertThatNoException().isThrownBy(() -> victim.validate(transactionRequest));
    }

    @Test
    void validate_whenAccountExists_andCurrenciesDoNotMatch_thenThrowCurrencyMismatchException() {
        var transactionRequest = mock(TransactionRequest.class);
        when(transactionRequest.sourceAccountId()).thenReturn(TRANSACTION_ID);
        var accountResponse = mock(AccountResponse.class);
        when(repository.findDtoById(TRANSACTION_ID)).thenReturn(Optional.of(accountResponse));
        when(accountResponse.currency()).thenReturn("EUR");
        when(transactionRequest.sourceCurrency()).thenReturn("USD");

        assertThatThrownBy(() -> victim.validate(transactionRequest))
                .isInstanceOf(CurrencyMismatchException.class)
                .hasMessageContaining("does not match");
    }



}
