package com.min.task.transaction.util;

import com.min.task.transaction.entity.TransactionEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransactionUtil {


    public void setValuesOnCreation(@NotNull TransactionEntity requestEntity, BigDecimal sourceAmount) {
        requestEntity.setId(UUID.randomUUID().toString());
        requestEntity.setCreated(LocalDateTime.now());
        requestEntity.setSourceAmount(sourceAmount.negate());
    }
}
