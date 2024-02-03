package com.min.task.transaction.repository;

import com.min.task.transaction.dto.TransactionResponse;
import com.min.task.transaction.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

    @Query("""
            SELECT new com.min.task.transaction.dto.TransactionResponse(
                t.id,
                t.sourceAccountEntity.id,
                t.destinationAccountEntity.id,
                t.sourceAmount,
                t.destinationAmount,
                t.created
            )
            FROM TransactionEntity t
            WHERE (t.sourceAccountEntity.id = :accountId OR t.destinationAccountEntity.id = :accountId)
            """)
    Page<TransactionResponse> findDtoListByAccountIdPaged(String accountId, Pageable pageable);

    @Query("""
            SELECT new com.min.task.transaction.dto.TransactionResponse(
                t.id,
                t.sourceAccountEntity.id,
                t.destinationAccountEntity.id,
                t.sourceAmount,
                t.destinationAmount,
                t.created
            )
            FROM TransactionEntity t
            WHERE t.id = :id
            """)
    Optional<TransactionResponse> findDtoById(@Param("id") String id);
}
