package com.min.task.transaction.service;

import com.min.task.currency.conversion.service.ConversionService;
import com.min.task.exception.TransactionIdDoesNotExistException;
import com.min.task.transaction.mapper.TransactionMapper;
import com.min.task.transaction.repository.TransactionRepository;
import com.min.task.transaction.util.TransactionUtil;
import com.min.task.transaction.validation.service.TransactionValidationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.min.task.test.util.TestUtil.ACCOUNT_ID;
import static com.min.task.test.util.TestUtil.TRANSACTION_ID;
import static com.min.task.test.util.TestUtil.testAccountEntity;
import static com.min.task.test.util.TestUtil.testTransactionEntity;
import static com.min.task.test.util.TestUtil.testTransactionRequest;
import static com.min.task.test.util.TestUtil.testTransactionResponse;
import static com.min.task.test.util.TestUtil.testUserEntity;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    TransactionRepository repository;
    @Mock
    TransactionValidationService validationService;
    @Mock
    TransactionMapper mapper;
    @Mock
    TransactionUtil util;
    @Mock
    ConversionService conversionService;

    @InjectMocks
    TransactionService victim;

    @AfterEach
    void windDown() {
        verifyNoMoreInteractions(repository, validationService, mapper, util, conversionService);
    }

    @Test
    void save_whenRequest_thenDelegate_andReturn() {
        var transactionRequest = testTransactionRequest();
        var user = testUserEntity();
        var sourceAccount = testAccountEntity(user);
        var destinationAccount = testAccountEntity(user);
        var transactionEntity = testTransactionEntity(sourceAccount, destinationAccount);

        doNothing().when(validationService).preValidate(transactionRequest);
        when(conversionService.convertToSourceAmount(transactionRequest)).thenReturn(BigDecimal.TEN);
        doNothing().when(validationService).postValidated(transactionRequest, BigDecimal.TEN);
        when(mapper.toEntity(transactionRequest)).thenReturn(transactionEntity);
        doNothing().when(util).setValuesOnCreation(transactionEntity, BigDecimal.TEN);
        when(repository.save(transactionEntity)).thenReturn(transactionEntity);

        var result = victim.save(transactionRequest);

        assertEquals(transactionEntity.getId(), result);
    }

    @Test
    void findById_whenFound_thenReturn() {
        var transactionResponse = testTransactionResponse();
        when(repository.findDtoById(TRANSACTION_ID)).thenReturn(Optional.of(transactionResponse));

        var result = victim.findById(TRANSACTION_ID);

        assertEquals(transactionResponse, result);
    }

    @Test
    void findById_whenNotFound_thenThrowTransactionIdDoesNotExistException() {
        when(repository.findDtoById(TRANSACTION_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findById(TRANSACTION_ID))
                .isInstanceOf(TransactionIdDoesNotExistException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void findPageByAccountId_whenFound_thenReturn() {
        var transactionResponse = testTransactionResponse();
        var pageable = mock(Pageable.class);
        var page = new PageImpl<>(List.of(transactionResponse));
        when(repository.findPageByAccountId(anyString(), any(Pageable.class)))
                .thenReturn(page);

        var result = victim.findPageByAccountId(ACCOUNT_ID, pageable);

        assertEquals(page, result);
    }
}
