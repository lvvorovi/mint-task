package com.min.task.user.service;

import com.min.task.user.model.UserCreateRequestDto;
import com.min.task.user.model.UserResponseDto;

public interface UserService {

    UserResponseDto findById(String id);

    UserResponseDto save(UserCreateRequestDto requestDto);

}
