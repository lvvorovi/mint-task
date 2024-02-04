package com.min.task.transaction.util;

import com.min.task.transaction.entity.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class TransactionUtilTest {

    @InjectMocks
    TransactionUtil victim;

    @Test
    void setValuesOnCreation_whenRequest_thenSet() {
        var transactionMock = mock(TransactionEntity.class);
        doNothing().when(transactionMock).setId(any());
        doNothing().when(transactionMock).setCreated(any());
        doNothing().when(transactionMock).setSourceAmount(any());

        victim.setValuesOnCreation(transactionMock, BigDecimal.ONE);

        verify(transactionMock, times(1)).setId(any());
        verify(transactionMock, times(1)).setCreated(any());
        verify(transactionMock, times(1)).setSourceAmount(BigDecimal.ONE.negate());
        verifyNoMoreInteractions(transactionMock);
    }

}
