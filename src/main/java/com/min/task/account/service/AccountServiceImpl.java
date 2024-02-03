package com.min.task.account.service;

import com.min.task.account.dto.AccountResponseDto;
import com.min.task.account.repository.AccountRepository;
import com.min.task.account.validation.AccountValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final AccountRepository repository;
    private final AccountValidationService validationService;


    @Override
    public List<AccountResponseDto> findAllByUserId(String userId) {
        validationService.validate(userId);
        var accountResponseDtoList = repository.findAllByUserId(userId);
        log.info("{} accounts found with userId:{}", accountResponseDtoList.size(), userId);
        return accountResponseDtoList;
    }

}
