package com.min.task.transaction.validation.rule.impl;

import com.min.task.account.dto.AccountResponse;
import com.min.task.account.repository.AccountRepository;
import com.min.task.exception.NotEnoughAccountBalanceException;
import com.min.task.transaction.dto.TransactionRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static com.min.task.test.util.TestUtil.ACCOUNT_ID;
import static com.min.task.test.util.TestUtil.testTransactionRequest;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PositiveBalanceSourceAccountTransactionPreValidationRuleTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    PositiveBalanceSourceAccountTransactionPreValidationRule victim;

    @Test
    void validate_whenAccountDoesNotExist_thenDoNothing() {
        var transactionRequest = testTransactionRequest();
        when(accountRepository.findDtoById(transactionRequest.sourceAccountId()))
                .thenReturn(Optional.empty());

        assertThatNoException().isThrownBy(() -> victim.validate(transactionRequest, BigDecimal.TEN));
    }

    @Test
    void validate_whenAccountExists_andBalanceIsEnough_thenDoNothing() {
        var transactionMock = mock(TransactionRequest.class);
        when(transactionMock.sourceAccountId()).thenReturn(ACCOUNT_ID);
        var accountMock = mock(AccountResponse.class);
        when(accountMock.balance()).thenReturn(BigDecimal.TEN);
        when(accountRepository.findDtoById(transactionMock.sourceAccountId()))
                .thenReturn(Optional.of(accountMock));

        assertThatNoException().isThrownBy(() -> victim.validate(transactionMock, BigDecimal.TEN));
    }

    @Test
    void validate_whenAccountExists_andBalanceIsNotEnough_thenThrowNotEnoughAccountBalanceException() {
        var transactionMock = mock(TransactionRequest.class);
        when(transactionMock.sourceAccountId()).thenReturn(ACCOUNT_ID);
        var accountMock = mock(AccountResponse.class);
        when(accountMock.balance()).thenReturn(BigDecimal.ONE);
        when(accountRepository.findDtoById(transactionMock.sourceAccountId()))
                .thenReturn(Optional.of(accountMock));

        assertThatThrownBy(() -> victim.validate(transactionMock, BigDecimal.TEN))
                .isInstanceOf(NotEnoughAccountBalanceException.class)
                .hasMessageContaining("Not enough balance");
    }

}
