package com.server.finderly_backend.data.repository;

import com.server.finderly_backend.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);
    boolean existsByNickName(String nickName);

}
