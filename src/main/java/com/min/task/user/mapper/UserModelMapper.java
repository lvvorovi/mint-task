package com.min.task.user.mapper;

import com.min.task.acccount.dto.AccountResponseDto;
import com.min.task.user.entity.UserEntity;
import com.min.task.user.dto.UserCreateRequestDto;
import com.min.task.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
        var dto = mapper.map(savedEntity, UserResponseDto.class);

        if (!ListUtils.emptyIfNull(savedEntity.getAccountEntityList()).isEmpty()) {
            var accountDtoList = savedEntity.getAccountEntityList()
                    .stream()
                    .map(account -> mapper.map(account, AccountResponseDto.class))
                    .toList();
            dto.setAccountResponseDtoList(accountDtoList);
        } else {
            dto.setAccountResponseDtoList(new ArrayList<>());
        }

        return dto;
    }
}
