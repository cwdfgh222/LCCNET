package com.example.libary.repository;

import com.example.libary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 檢查使用者名稱是否已存在
    boolean existsByUsername(String username);
    
    // 透過使用者名稱查找使用者
    Optional<User> findByUsername(String username);
}