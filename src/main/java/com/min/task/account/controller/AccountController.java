package com.min.task.account.controller;

import com.min.task.account.dto.AccountResponse;
import com.min.task.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/accounts")
public class AccountController {

    private final AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAllByUserId(@PathVariable String userId) {
        var responseDtoList = service.findAllByUserId(userId);

        if (responseDtoList.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            return ResponseEntity
                    .ok(responseDtoList);
        }
    }
}
