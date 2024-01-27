package com.min.task.user.validation.rule;

import com.min.task.user.model.UserCreateRequestDto;

public interface UserCreateRequestValidationRule {

    void validate(UserCreateRequestDto requestDto);
}
