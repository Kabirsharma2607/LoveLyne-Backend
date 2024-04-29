package com.example.backend.apis;

import com.example.backend.models.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeedController {

    @Autowired
    private UserService userService;

    @GetMapping("/feed")
    public List<User> getFeed(@RequestParam("userId") String userId) {
        System.out.println("In feed" + userId);

        return userService.getUsersForFeed(userId);
    }
}
