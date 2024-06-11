package com.server.finderly_backend.data.repository;

import com.server.finderly_backend.data.entity.User;
import com.server.finderly_backend.dto.user.UserProfileDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);
    boolean existsByNickName(String nickName);
}
