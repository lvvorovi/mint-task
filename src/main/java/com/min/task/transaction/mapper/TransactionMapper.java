package com.min.task.transaction.mapper;

import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.entity.TransactionEntity;

public interface TransactionMapper {

    TransactionEntity toEntity(TransactionRequest transactionRequest);

}
