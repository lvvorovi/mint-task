package com.min.task.user.validation.service;

import com.min.task.user.dto.UserCreateRequestDto;
import com.min.task.user.validation.rule.UserCreateRequestValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidationServiceImpl implements UserValidationService {

    private final List<UserCreateRequestValidationRule> createRequestValidationRuleList;

    @Override
    public void validate(UserCreateRequestDto requestDto) {
        createRequestValidationRuleList.forEach(rule -> rule.validate(requestDto));
    }
}
