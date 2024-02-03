package com.min.task.transaction.mapper;

import com.min.task.account.entity.AccountEntity;
import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomTransactionMapper implements TransactionMapper {

    @Override
    public TransactionEntity toEntity(TransactionRequest transactionRequest) {
        var entity = new TransactionEntity();
        entity.setSourceAmount(transactionRequest.amount().negate());

        var sourceAccountEntity = new AccountEntity();
        sourceAccountEntity.setId(transactionRequest.sourceAccountId());
        entity.setSourceAccountEntity(sourceAccountEntity);

        var destinationAccountEntity = new AccountEntity();
        destinationAccountEntity.setId(transactionRequest.destinationAccountId());
        entity.setDestinationAccountEntity(destinationAccountEntity);

        return entity;
    }
}
