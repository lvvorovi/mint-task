package com.min.task.transaction.service;

import com.min.task.transaction.dto.TransactionResponseDto;
import com.min.task.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public Page<TransactionResponseDto> findPageByAccountId(String accountId, Pageable pageable) {
        var transactionDtoPage = repository.findDtoListByAccountIdPaged(accountId, pageable);
        log.info("{} transactions found for accountId:{}", transactionDtoPage.getContent().size(), accountId);
        return transactionDtoPage;
    }
}
