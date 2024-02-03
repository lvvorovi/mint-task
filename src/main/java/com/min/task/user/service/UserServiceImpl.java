package com.min.task.user.service;

import com.min.task.exception.UserDoesNotExistException;
import com.min.task.user.dto.UserCreateRequestDto;
import com.min.task.user.dto.UserResponseDto;
import com.min.task.user.mapper.UserMapper;
import com.min.task.user.repository.UserRepository;
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
        var userDto = repository.findDtoById(id);
        if (userDto.isEmpty()) {
            throw new UserDoesNotExistException("User with id:%s does not exist".formatted(id));
        }
        return userDto.get();
    }

    @Override
    public String save(UserCreateRequestDto requestDto) {
        validationService.validate(requestDto);
        var requestEntity = mapper.toEntity(requestDto);
        requestEntity.setId(UUID.randomUUID().toString());
        var savedEntity = repository.save(requestEntity);
        log.info("User saved with id:{}", savedEntity.getId());
        return savedEntity.getId();
    }
}
