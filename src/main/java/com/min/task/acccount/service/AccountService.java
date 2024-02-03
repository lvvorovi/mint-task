package com.min.task.acccount.service;

import com.min.task.acccount.dto.AccountResponseDto;

import java.util.List;

public interface AccountService {

    List<AccountResponseDto> findAllByUserId(String userId);

}
