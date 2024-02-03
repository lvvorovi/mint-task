package com.min.task.user.mapper;

import com.min.task.user.dto.UserCreateRequest;
import com.min.task.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserModelMapper implements UserMapper {

    private final ModelMapper mapper;

    @Override
    public UserEntity toEntity(UserCreateRequest requestDto) {
        return mapper.map(requestDto, UserEntity.class);
    }

}
