package com.min.task.user.validation.rule;

import com.min.task.user.dto.UserCreateRequest;

public interface UserCreateRequestValidationRule {

    void validate(UserCreateRequest requestDto);
}
