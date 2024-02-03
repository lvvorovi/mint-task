package com.min.task.acccount.repository;

import com.min.task.acccount.dto.AccountResponseDto;
import com.min.task.acccount.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    @Query("""
            SELECT new com.min.task.acccount.dto.AccountResponseDto(
                a.id,
                new com.min.task.user.dto.UserResponseDto(u.id, u.name),
                a.currency,
                COALESCE(SUM(t.amount), 0)
            )
            FROM AccountEntity a
            LEFT JOIN UserEntity u ON u.id = a.userEntity.id
            LEFT JOIN TransactionEntity t ON t.accountEntity.id = a.id
            WHERE u.id = :userId
            GROUP BY a.id, u.id, u.name, a.currency
            ORDER BY currency ASC
            """)
    List<AccountResponseDto> findAllByUserId(@Param("userId") String userId);
}
