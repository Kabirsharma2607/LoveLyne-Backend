package com.example.backend.repository;

import com.example.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
    // You can add custom query methods here if needed
    User findByEmail(String email);

    User findByUserId(String userId);
    List<User> findByUserIdIn(List<String> userIds);
}
