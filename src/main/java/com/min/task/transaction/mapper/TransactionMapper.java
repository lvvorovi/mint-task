package com.min.task.transaction.mapper;

import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.entity.TransactionEntity;
import jakarta.validation.constraints.NotNull;

public interface TransactionMapper {

    TransactionEntity toEntity(@NotNull TransactionRequest transactionRequest);

}
