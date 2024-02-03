package com.min.task.acccount.validation.rule.impl;

import com.min.task.acccount.validation.rule.AccountUserIdValidationRule;
import com.min.task.exception.UserDoesNotExistException;
import com.min.task.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountUserDoesNotExistValidationRule implements AccountUserIdValidationRule {

    private final UserRepository userRepository;

    @Override
    public void validate(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserDoesNotExistException("User with id:%s does not exist".formatted(userId));
        }
    }
}
