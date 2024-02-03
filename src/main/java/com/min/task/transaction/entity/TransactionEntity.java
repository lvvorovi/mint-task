package com.min.task.transaction.entity;

import com.min.task.acccount.entity.AccountEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class TransactionEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    private AccountEntity accountEntity;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created", updatable = false, nullable = false)
    private LocalDateTime created;
}
