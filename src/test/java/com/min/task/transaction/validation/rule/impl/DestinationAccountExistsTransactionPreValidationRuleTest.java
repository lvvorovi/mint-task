package com.min.task.transaction.validation.rule.impl;

import com.min.task.account.repository.AccountRepository;
import com.min.task.exception.AccountIdDoesNotExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.min.task.test.util.TestUtil.testTransactionRequest;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DestinationAccountExistsTransactionPreValidationRuleTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    DestinationAccountExistsTransactionPreValidationRule victim;

    @Test
    void validate_whenExists_thenDoNothing() {
        var transactionRequest = testTransactionRequest();
        when(accountRepository.existsById(transactionRequest.destinationAccountId()))
                .thenReturn(true);

        assertThatNoException().isThrownBy(() -> victim.validate(transactionRequest));
    }

    @Test
    void validate_whenDoesNotExist_thenThrowAccountIdDoesNotExistException() {
        var transactionRequest = testTransactionRequest();
        when(accountRepository.existsById(transactionRequest.destinationAccountId()))
                .thenReturn(false);

        assertThatThrownBy(() -> victim.validate(transactionRequest))
                .isInstanceOf(AccountIdDoesNotExistException.class)
                .hasMessageContaining("does not exist");
    }
}
