package com.min.task.user.repository;

import com.min.task.user.dto.UserResponseDto;
import com.min.task.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByName(String name);

    @Query("""
            SELECT new com.min.task.user.dto.UserResponseDto(
                u.id,
                u.name
            )
            FROM UserEntity u
            WHERE u.id = :id
            """)
    Optional<UserResponseDto> findDtoById(@Param("id") String id);
}
