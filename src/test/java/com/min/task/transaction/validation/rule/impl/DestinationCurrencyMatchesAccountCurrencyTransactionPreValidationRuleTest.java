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

import static com.min.task.test.util.TestUtil.testTransactionRequest;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DestinationCurrencyMatchesAccountCurrencyTransactionPreValidationRuleTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    DestinationCurrencyMatchesAccountCurrencyTransactionPreValidationRule victim;

    @Test
    void validate_whenAccountDoesNotExist_thenDoNothing() {
        var transactionRequest = testTransactionRequest();
        when(accountRepository.findDtoById(transactionRequest.destinationAccountId()))
                .thenReturn(Optional.empty());

        assertThatNoException().isThrownBy(() -> victim.validate(transactionRequest));
    }

    @Test
    void validate_whenAccountExists_andCurrencyMatches_thenDoNothing() {
        var transactionRequest = mock(TransactionRequest.class);
        var accountResponse = mock(AccountResponse.class);
        when(transactionRequest.destinationCurrency()).thenReturn("EUR");
        when(accountResponse.currency()).thenReturn("eur");
        when(accountRepository.findDtoById(transactionRequest.destinationAccountId()))
                .thenReturn(Optional.of(accountResponse));

        assertThatNoException().isThrownBy(() -> victim.validate(transactionRequest));
    }

    @Test
    void validate_whenAccountExists_andCurrencyDoesNotMatch_thenThrowCurrencyMismatchException() {
        var transactionRequest = mock(TransactionRequest.class);
        var accountResponse = mock(AccountResponse.class);
        when(transactionRequest.destinationCurrency()).thenReturn("EUR");
        when(accountResponse.currency()).thenReturn("usd");
        when(accountRepository.findDtoById(transactionRequest.destinationAccountId()))
                .thenReturn(Optional.of(accountResponse));

        assertThatThrownBy(() -> victim.validate(transactionRequest))
                .isInstanceOf(CurrencyMismatchException.class)
                .hasMessageContaining("does not match");
    }

}
