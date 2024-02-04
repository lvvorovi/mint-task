package com.min.task.transaction.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.min.task.test.util.TestUtil.testTransactionRequest;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomTransactionMapperTest {


    @InjectMocks
    CustomTransactionMapper victim;

    @Test
    void toEntity() {
        var transactionRequest = testTransactionRequest();

        var result = victim.toEntity(transactionRequest);

        assertEquals(transactionRequest.destinationAccountId(), result.getDestinationAccountEntity().getId());
        assertEquals(transactionRequest.sourceAccountId(), result.getSourceAccountEntity().getId());
        assertEquals(transactionRequest.amount(), result.getDestinationAmount());
    }
}
