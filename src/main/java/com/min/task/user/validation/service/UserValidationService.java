package com.min.task.user.validation.service;

import com.min.task.user.dto.UserCreateRequestDto;

public interface UserValidationService {

    void validate(UserCreateRequestDto requestDto);
}
