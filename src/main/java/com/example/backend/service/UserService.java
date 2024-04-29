package com.example.backend.service;

import com.example.backend.models.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Document(collection = "users")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<String> getMatches(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        query.fields().include("matches");

        User user = mongoTemplate.findOne(query, User.class);
        return user != null ? user.getMatches() : Collections.emptyList();
    }


    @Autowired
    private MongoTemplate mongoTemplate;

    public void addToSeenList(String userId, String swipedUserId) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("userId").is(userId)),
                new Update().addToSet("seen", swipedUserId),
                User.class
        );
    }

    public void updateMatchStatus(String userId, String swipedUserId) {
        mongoTemplate.updateFirst(
                    Query.query(Criteria.where("userId").is(userId)),
                    new Update().addToSet("matches", swipedUserId),
                    User.class
        );

    }


    public List<User> getUsersForFeed(String userId) {
        // Retrieve the current user
        User currentUser = userRepository.findByUserId(userId);

        if (currentUser == null) {
            return Collections.emptyList(); // User not found, return empty list
        }

        // Get the list of users who have not been seen by the current user
        List<User> unseenUsers = userRepository.findAll().stream()
                .filter(user -> !currentUser.getSeen().contains(user.getUserId()))
                .collect(Collectors.toList());

        // Exclude the current user from the list if necessary
        unseenUsers.remove(currentUser);

        return unseenUsers;
    }


    public boolean signUp(User user) {
        User existingUser = userRepository.findByUserId(user.getUserId());
        if (existingUser != null) {
            return false;
        }
        System.out.println("IN signup");
        System.out.println(user);
        userRepository.save(user);

        return true;
    }


    public boolean authenticate(String userId, String password) {
        System.out.println(userId + " " + password);
        User user = userRepository.findByUserId(userId);

        System.out.println(user);
        if (user == null) {
            return false;
        }

        String hashedProvidedPassword = hashPassword(password);

        return user.getHashedPassword().equals(hashedProvidedPassword);
    }

    private String hashPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hashed password bytes
            byte[] hashedBytes = md.digest();

            // Convert byte array to base64 representation
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getMutualMatches(String userId) {
        User currentUser = userRepository.findByUserId(userId);
        if (currentUser == null) {
            return Collections.emptyList();
        }
        List<String> currentUserMatches = currentUser.getMatches();
        if (currentUserMatches == null || currentUserMatches.isEmpty()) {
            return Collections.emptyList();
        }
        // Get users who have also matched with the current user
        List<User> mutualMatches = userRepository.findByUserIdIn(currentUserMatches);

        return mutualMatches.stream()
                .filter(user -> user.getMatches() != null && user.getMatches().contains(userId))
                .collect(Collectors.toList());
    }

}
