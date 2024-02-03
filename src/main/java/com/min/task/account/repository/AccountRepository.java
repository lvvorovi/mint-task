package com.min.task.account.repository;

import com.min.task.account.dto.AccountResponse;
import com.min.task.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    @Query("""
                SELECT new com.min.task.account.dto.AccountResponse(
                    a.id,
                    new com.min.task.user.dto.UserResponse(u.id, u.name),
                    a.currency,
                    COALESCE(
                        COALESCE(SUM(CASE WHEN t.sourceAccountEntity.id = a.id THEN t.sourceAmount ELSE 0 END), 0) +
                        COALESCE(SUM(CASE WHEN t.destinationAccountEntity.id = a.id THEN t.destinationAmount ELSE 0 END), 0)
                    , 0
                    )
                )
                FROM AccountEntity a
                LEFT JOIN TransactionEntity t ON
                    (t.sourceAccountEntity.id = a.id OR t.destinationAccountEntity.id = a.id)
                LEFT JOIN UserEntity u ON u.id = a.userEntity.id
                WHERE u.id = :userId
                GROUP BY a.id, u.id, u.name, a.currency
                ORDER BY a.currency ASC
            """)
    List<AccountResponse> findAllByUserId(@Param("userId") String userId);


    @Query("""
                SELECT new com.min.task.account.dto.AccountResponse(
                    a.id,
                    new com.min.task.user.dto.UserResponse(u.id, u.name),
                    a.currency,
                    COALESCE(
                        COALESCE(SUM(CASE WHEN t.sourceAccountEntity.id = a.id THEN t.sourceAmount ELSE 0 END), 0) +
                        COALESCE(SUM(CASE WHEN t.destinationAccountEntity.id = a.id THEN t.destinationAmount ELSE 0 END), 0)
                    , 0
                    )
                )
                FROM AccountEntity a
                LEFT JOIN TransactionEntity t ON
                    (t.sourceAccountEntity.id = a.id OR t.destinationAccountEntity.id = a.id)
                LEFT JOIN UserEntity u ON u.id = a.userEntity.id
                WHERE a.id = :id
                GROUP BY a.id, a.currency
            """)
    Optional<AccountResponse> findDtoById(@Param("id") String id);
}
