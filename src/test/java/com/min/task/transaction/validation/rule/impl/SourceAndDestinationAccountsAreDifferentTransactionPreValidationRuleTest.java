package com.min.task.transaction.validation.rule.impl;

import com.min.task.exception.BadRequestException;
import com.min.task.transaction.dto.TransactionRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.min.task.test.util.TestUtil.ACCOUNT_ID;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SourceAndDestinationAccountsAreDifferentTransactionPreValidationRuleTest {

    @InjectMocks
    SourceAndDestinationAccountsAreDifferentTransactionPreValidationRule victim;

    @Test
    void validate_whenAreNotEqual_thenDoNothing() {
        var transactionMock = mock(TransactionRequest.class);
        when(transactionMock.sourceAccountId()).thenReturn("sourceAccount");
        when(transactionMock.destinationAccountId()).thenReturn("destinationAccount");

        assertThatNoException().isThrownBy(() -> victim.validate(transactionMock));
    }

    @Test
    void validate_whenEqual_thenThrownBadRequestException() {
        var transactionMock = mock(TransactionRequest.class);
        when(transactionMock.sourceAccountId()).thenReturn(ACCOUNT_ID);
        when(transactionMock.destinationAccountId()).thenReturn(ACCOUNT_ID);

        assertThatThrownBy(() -> victim.validate(transactionMock))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("not allowed");
    }

}
