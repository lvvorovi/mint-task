package com.min.task.user.validation.rule.impl;

import com.min.task.exception.BadRequestException;
import com.min.task.user.entity.repository.UserRepository;
import com.min.task.user.model.UserCreateRequestDto;
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
            throw new BadRequestException("User with name %s already exists".formatted(requestDto.name()));
        }
    }
}
