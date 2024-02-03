package com.min.task.user.mapper;

import com.min.task.user.dto.UserCreateRequest;
import com.min.task.user.entity.UserEntity;

public interface UserMapper {
    UserEntity toEntity(UserCreateRequest requestDto);

}
