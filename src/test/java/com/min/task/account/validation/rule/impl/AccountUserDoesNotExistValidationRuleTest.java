package com.min.task.account.validation.rule.impl;

import com.min.task.exception.UserDoesNotExistException;
import com.min.task.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.min.task.test.util.TestUtil.USER_ID;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountUserDoesNotExistValidationRuleTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AccountUserDoesNotExistValidationRule victim;

    @Test
    void validate_whenUserExists_doNothing() {
        when(userRepository.existsById(USER_ID)).thenReturn(true);

        assertDoesNotThrow(() -> victim.validate(USER_ID));
    }

    @Test
    void validate_whenUserDoesNotExist_throwUserDoesNotExistException() {
        when(userRepository.existsById(USER_ID)).thenReturn(false);

        assertThatThrownBy(() -> victim.validate(USER_ID))
                .isInstanceOf(UserDoesNotExistException.class)
                .hasMessageContaining("does not exist");
    }

}
