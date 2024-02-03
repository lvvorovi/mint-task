package com.min.task.user.service;

import com.min.task.user.dto.UserCreateRequestDto;
import com.min.task.user.dto.UserResponseDto;

public interface UserService {

    UserResponseDto findById(String id);

    String save(UserCreateRequestDto requestDto);

}
