package com.min.task.account.service;

import com.min.task.account.repository.AccountRepository;
import com.min.task.account.validation.AccountValidationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.min.task.test.util.TestUtil.USER_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {


    @Mock
    AccountRepository repository;
    @Mock
    AccountValidationService validationService;

    @InjectMocks
    AccountService victim;

    @AfterEach
    void windDown() {
        verifyNoMoreInteractions(repository, validationService);
    }

    @Test
    void findAllByUserId_whenRequest_thenValidate_callRepo_andReturn() {
        doNothing().when(validationService).validate(any());
        when(repository.findAllByUserId(USER_ID)).thenReturn(List.of());
        var expected = List.of();

        var result = victim.findAllByUserId(USER_ID);

        assertEquals(expected, result);
    }
}
