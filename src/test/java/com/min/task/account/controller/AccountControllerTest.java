package com.min.task.account.controller;

import com.min.task.account.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.min.task.test.util.TestUtil.USER_ID;
import static com.min.task.test.util.TestUtil.testAccountResponse;
import static com.min.task.test.util.TestUtil.testUserResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {


    @Mock
    AccountService service;

    @InjectMocks
    AccountController victim;

    @AfterEach
    void windDown() {
        verifyNoMoreInteractions(service);
    }

    @Test
    void findAllByUserId_whenFound_return200() {
        var user = testUserResponse();
        var accountA = testAccountResponse(user);
        var accountB = testAccountResponse(user);
        when(service.findAllByUserId(USER_ID)).thenReturn(List.of(accountA, accountB));
        var expected = ResponseEntity.ok(List.of(accountA, accountB));

        var result = victim.findAllByUserId(USER_ID);

        assertEquals(expected, result);
    }

    @Test
    void findAllByUserId_whenFound_return204() {
        when(service.findAllByUserId(USER_ID)).thenReturn(List.of());
        var expected = ResponseEntity.noContent().build();

        var result = victim.findAllByUserId(USER_ID);

        assertEquals(expected, result);
    }


}
