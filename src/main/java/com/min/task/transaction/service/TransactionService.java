package com.min.task.transaction.service;

import com.min.task.transaction.dto.TransactionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    Page<TransactionResponseDto> findPageByAccountId(String accountId, Pageable pageable);

}
