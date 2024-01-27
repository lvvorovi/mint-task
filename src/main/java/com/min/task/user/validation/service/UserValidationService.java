package com.min.task.user.validation.service;

import com.min.task.user.model.UserCreateRequestDto;

public interface UserValidationService {

    void validate(UserCreateRequestDto requestDto);
}
