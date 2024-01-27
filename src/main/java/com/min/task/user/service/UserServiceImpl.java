package com.min.task.user.service;

import com.min.task.exception.BadRequestException;
import com.min.task.user.entity.repository.UserRepository;
import com.min.task.user.mapper.UserMapper;
import com.min.task.user.model.UserCreateRequestDto;
import com.min.task.user.model.UserResponseDto;
import com.min.task.user.validation.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserValidationService validationService;


    @Override
    public UserResponseDto findById(String id) {
        var userDto = repository.findById(id);
        if (userDto.isEmpty()) {
            throw new BadRequestException("User with id:%s does not exist".formatted(id));
        }
        return mapper.toDto(userDto.get());
    }

    @Override
    public UserResponseDto save(UserCreateRequestDto requestDto) {
        validationService.validate(requestDto);
        var requestEntity = mapper.toEntity(requestDto);
        requestEntity.setId(UUID.randomUUID().toString());
        var savedEntity = repository.save(requestEntity);
        log.info("User saved with id:{}", savedEntity.getId());
        return mapper.toDto(savedEntity);
    }
}
