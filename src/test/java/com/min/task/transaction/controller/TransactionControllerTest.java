package com.min.task.transaction.controller;

import com.min.task.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.min.task.test.util.TestUtil.ACCOUNT_ID;
import static com.min.task.test.util.TestUtil.TRANSACTION_ID;
import static com.min.task.test.util.TestUtil.USER_ID;
import static com.min.task.test.util.TestUtil.testTransactionRequest;
import static com.min.task.test.util.TestUtil.testTransactionResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    TransactionService service;

    @InjectMocks
    TransactionController victim;

    @Test
    void save_whenRequest_passToService_andReturn() {
        var transactionRequest = testTransactionRequest();
        var uriMock = mock(UriComponentsBuilder.class);
        var uriComponentsMock = mock(UriComponents.class);
        when(uriMock.path(anyString())).thenReturn(uriMock);
        when(uriMock.buildAndExpand(anyString(), anyString())).thenReturn(uriComponentsMock);
        when(uriComponentsMock.toUri()).thenReturn(URI.create("uri"));
        when(service.save(transactionRequest)).thenReturn(TRANSACTION_ID);
        var expected = ResponseEntity
                .created(URI.create("uri"))
                .build();

        var result = victim.save(transactionRequest, USER_ID, uriMock);

        assertEquals(expected, result);
    }

    @Test
    void findById_whenFound_thenReturn() {
        var transactionResponse = testTransactionResponse();
        when(service.findById(transactionResponse.id())).thenReturn(transactionResponse);
        var expected = ResponseEntity
                .ok(transactionResponse);

        var result = victim.findById(transactionResponse.id());

        assertEquals(expected, result);
    }

    @Test
    void findPageByAccountId_whenNoParams_thenReturnEmptyPage() {
        when(service.findPageByAccountId(ACCOUNT_ID, null)).thenReturn(Page.empty());
        var expecter = ResponseEntity
                .noContent()
                .build();

        var result = victim
                .findPageByAccountId(ACCOUNT_ID, null, null, null);

        assertEquals(expecter, result);
    }

    @Test
    void findPageByAccountId_whenNoParams_thenReturnNonEmptyPage() {
        var page = new PageImpl<>(List.of(testTransactionResponse()));
        when(service.findPageByAccountId(ACCOUNT_ID, null))
                .thenReturn(page);

        var expected = ResponseEntity
                .ok(page);

        var result = victim
                .findPageByAccountId(ACCOUNT_ID, null, null, null);

        assertEquals(expected, result);
    }

    @Test
    void findPageByAccountId_whenParams_thenSubstitutePageable() {
        var page = new PageImpl<>(List.of(testTransactionResponse()));
        when(service.findPageByAccountId(anyString(), any()))
                .thenReturn(page);

        victim.findPageByAccountId(ACCOUNT_ID, null, 2, 0);

        verify(service, never()).findPageByAccountId(ACCOUNT_ID, null);
    }

}
