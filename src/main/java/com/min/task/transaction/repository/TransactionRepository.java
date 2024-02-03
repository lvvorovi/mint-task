package com.min.task.transaction.repository;

import com.min.task.transaction.dto.TransactionResponseDto;
import com.min.task.transaction.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

    @Query("""
            SELECT new com.min.task.transaction.dto.TransactionResponseDto(
                t.id,
                a.id,
                a.currency,
                t.amount,
                t.created
            )
            FROM TransactionEntity t
            LEFT JOIN AccountEntity a ON a.id = t.accountEntity.id
            WHERE a.id = :accountId
            """)
    Page<TransactionResponseDto> findDtoListByAccountIdPaged(String accountId, Pageable pageable);

}
