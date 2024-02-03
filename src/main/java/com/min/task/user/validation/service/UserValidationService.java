package com.min.task.user.validation.service;

import com.min.task.user.dto.UserCreateRequest;

public interface UserValidationService {

    void validate(UserCreateRequest requestDto);
}
