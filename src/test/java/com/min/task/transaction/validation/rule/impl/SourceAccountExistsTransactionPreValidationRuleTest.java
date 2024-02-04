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
class SourceAccountExistsTransactionPreValidationRuleTest {

    @Mock
    AccountRepository repository;

    @InjectMocks
    SourceAccountExistsTransactionPreValidationRule victim;

    @Test
    void validate_whenAccountExists_thenDoNothin() {
        var transactionRequest = testTransactionRequest();
        when(repository.existsById(transactionRequest.sourceAccountId())).thenReturn(true);

        assertThatNoException().isThrownBy(() -> victim.validate(transactionRequest));
    }

    @Test
    void validate_whenAccountDoesNotExist_thenThrownAccountIdDoesNotExistException() {
        var transactionRequest = testTransactionRequest();
        when(repository.existsById(transactionRequest.sourceAccountId())).thenReturn(false);

        assertThatThrownBy(() -> victim.validate(transactionRequest))
                .isInstanceOf(AccountIdDoesNotExistException.class)
                .hasMessageContaining("does not exist");
    }

}
