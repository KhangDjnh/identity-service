package com.devteria.indentity_service.repository;

import com.devteria.indentity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByIdAndIsActive(String id, Boolean isActive);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.username <> 'admin'")
    void deleteAllExceptAdmin();
}

