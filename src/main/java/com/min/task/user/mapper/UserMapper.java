package com.min.task.user.mapper;

import com.min.task.user.entity.UserEntity;
import com.min.task.user.model.UserCreateRequestDto;
import com.min.task.user.model.UserResponseDto;

public interface UserMapper {
    UserEntity toEntity(UserCreateRequestDto requestDto);

    UserResponseDto toDto(UserEntity savedEntity);
}
