package com.min.task.transaction.service;

import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.dto.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    Page<TransactionResponse> findPageByAccountId(String accountId, Pageable pageable);

    String save(TransactionRequest transactionRequest);

    TransactionResponse findById(String id);
}
