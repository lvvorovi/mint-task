package com.min.task.user.validation.rule.impl;

import com.min.task.exception.UserNameAlreadyRegisteredException;
import com.min.task.user.dto.UserCreateRequestDto;
import com.min.task.user.repository.UserRepository;
import com.min.task.user.validation.rule.UserCreateRequestValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserNameAlreadyExistValidationRule implements UserCreateRequestValidationRule {

    private final UserRepository repository;

    @Override
    public void validate(UserCreateRequestDto requestDto) {
        if (repository.existsByName(requestDto.name())) {
            throw new UserNameAlreadyRegisteredException(
                    "User with name %s already exists".formatted(requestDto.name()));
        }
    }
}
