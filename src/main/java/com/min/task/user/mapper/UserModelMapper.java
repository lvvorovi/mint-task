package com.min.task.user.mapper;

import com.min.task.user.entity.UserEntity;
import com.min.task.user.model.UserCreateRequestDto;
import com.min.task.user.model.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserModelMapper implements UserMapper {

    private final ModelMapper mapper;

    @Override
    public UserEntity toEntity(UserCreateRequestDto requestDto) {
        return mapper.map(requestDto, UserEntity.class);
    }

    @Override
    public UserResponseDto toDto(UserEntity savedEntity) {
        return mapper.map(savedEntity, UserResponseDto.class);
    }
}
