package com.min.task.acccount.service;

import com.min.task.acccount.dto.AccountResponseDto;
import com.min.task.acccount.repository.AccountRepository;
import com.min.task.acccount.validation.AccountValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
