package com.min.task.user.mapper;

import com.min.task.user.entity.UserEntity;
import com.min.task.user.dto.UserCreateRequestDto;
import com.min.task.user.dto.UserResponseDto;

public interface UserMapper {
    UserEntity toEntity(UserCreateRequestDto requestDto);

    UserResponseDto toDto(UserEntity savedEntity);
}
