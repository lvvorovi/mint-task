package com.min.task.account.entity;

import com.min.task.transaction.entity.TransactionEntity;
import com.min.task.user.entity.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "accounts")
@Data
public class AccountEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "sourceAccountEntity", cascade = CascadeType.REMOVE)
    List<TransactionEntity> outgoingTransactionEntityList;

    @OneToMany(mappedBy = "destinationAccountEntity", cascade = CascadeType.REMOVE)
    List<TransactionEntity> incomingTransactionEntityList;

    @Column(name = "currency")
    private String currency;

}
