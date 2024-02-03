package com.min.task.account.service;

import com.min.task.account.dto.AccountResponseDto;

import java.util.List;

public interface AccountService {

    List<AccountResponseDto> findAllByUserId(String userId);

}
