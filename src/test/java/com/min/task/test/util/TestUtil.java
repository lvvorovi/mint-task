package com.min.task.test.util;

import com.min.task.account.dto.AccountResponse;
import com.min.task.account.entity.AccountEntity;
import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.dto.TransactionResponse;
import com.min.task.transaction.entity.TransactionEntity;
import com.min.task.user.dto.UserResponse;
import com.min.task.user.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TestUtil {

    public static final String USER_ID = "9bd7ed62-22b8-48aa-ab14-cced9b69c242";
    public static final String TRANSACTION_ID = "0aebcf68-3dec-4dbc-8589-7bd15c60be42";
    public static final String ACCOUNT_ID = "ebfb8525-13a2-408e-b53b-54d0dc7f1df4";
    public static final String USER_NAME = "testUserName";


    public static AccountResponse testAccountResponse(UserResponse userResponse) {
        return new AccountResponse(
                UUID.randomUUID().toString(),
                userResponse,
                "EUR",
                BigDecimal.TEN
        );
    }

    public static UserResponse testUserResponse() {
        return new UserResponse(
                UUID.randomUUID().toString(),
                USER_NAME
        );
    }

    public static TransactionRequest testTransactionRequest() {
        return new TransactionRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "EUR",
                "USD",
                BigDecimal.TEN
        );
    }

    public static TransactionResponse testTransactionResponse() {
        return new TransactionResponse(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                BigDecimal.TEN,
                BigDecimal.TEN,
                LocalDateTime.now()
        );
    }

    public static TransactionEntity testTransactionEntity(AccountEntity source, AccountEntity destination) {
        var entity = new TransactionEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setDestinationAmount(BigDecimal.TEN);
        entity.setSourceAmount(BigDecimal.TEN);
        entity.setCreated(LocalDateTime.now());
        entity.setSourceAccountEntity(source);
        entity.setDestinationAccountEntity(destination);

        return entity;
    }

    public static AccountEntity testAccountEntity(UserEntity user) {
        var entity = new AccountEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setUserEntity(user);
        entity.setCurrency("EUR");

        return entity;
    }

    public static UserEntity testUserEntity() {
        var entity = new UserEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setName(USER_NAME);

        return entity;
    }


}
