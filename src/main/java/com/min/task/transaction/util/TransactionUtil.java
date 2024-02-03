package com.min.task.transaction.util;

import com.min.task.transaction.entity.TransactionEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransactionUtil {


    public void setValuesOnCreation(TransactionEntity requestEntity, BigDecimal destinationAmount) {

        requestEntity.setId(UUID.randomUUID().toString());
        requestEntity.setCreated(LocalDateTime.now());
        requestEntity.setDestinationAmount(destinationAmount);

    }
}
