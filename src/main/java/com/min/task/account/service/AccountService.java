package com.min.task.account.service;

import com.min.task.account.dto.AccountResponse;
import com.min.task.account.repository.AccountRepository;
import com.min.task.account.validation.AccountValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {


    private final AccountRepository repository;
    private final AccountValidationService validationService;


    public List<AccountResponse> findAllByUserId(String userId) {
        validationService.validate(userId);
        var accountResponseDtoList = repository.findAllByUserId(userId);
        log.info("{} accounts found with userId:{}", accountResponseDtoList.size(), userId);
        return accountResponseDtoList;
    }

}
