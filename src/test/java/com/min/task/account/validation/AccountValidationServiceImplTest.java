package com.min.task.account.validation;

import com.min.task.account.validation.rule.AccountUserIdValidationRule;
import com.min.task.account.validation.rule.impl.AccountUserDoesNotExistValidationRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.min.task.test.util.TestUtil.USER_ID;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountValidationServiceImplTest {

    @Mock
    AccountUserDoesNotExistValidationRule accountUserDoesNotExistValidationRule;

    List<AccountUserIdValidationRule> accountUserIdValidationRuleList;
    AccountValidationServiceImpl victim;

    @BeforeEach
    void setUp() {
        accountUserIdValidationRuleList = new ArrayList<>();
        accountUserIdValidationRuleList.add(accountUserDoesNotExistValidationRule);
        victim = new AccountValidationServiceImpl(accountUserIdValidationRuleList);
    }

    @AfterEach
    void windDown() {
        accountUserIdValidationRuleList.forEach(Mockito::verifyNoMoreInteractions);
    }

    @Test
    void validate_whenCalled_passToEachRuleForValidation() {
        accountUserIdValidationRuleList
                .forEach(rule -> doNothing().when(rule).validate(USER_ID));

        victim.validate(USER_ID);

        accountUserIdValidationRuleList.forEach(rule -> {
            verify(rule, times(1)).validate(USER_ID);
        });
    }

}
