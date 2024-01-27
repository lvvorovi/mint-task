package com.min.task.user.controller;

import com.min.task.user.model.UserCreateRequestDto;
import com.min.task.user.model.UserResponseDto;
import com.min.task.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody @Valid UserCreateRequestDto requestDto,
                                                UriComponentsBuilder builder) {
        var responseDto = service.save(requestDto);

        return ResponseEntity
                .created(builder
                        .path("/api/v1/users/{id}")
                        .buildAndExpand(responseDto.getId())
                        .toUri())
                .build();
    }

}
