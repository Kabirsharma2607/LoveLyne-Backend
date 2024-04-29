package com.example.backend.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.models.User;
import com.example.backend.service.UserService;
import java.util.List;

@RestController
public class MatchedFeedController {

    @Autowired
    private UserService userService;

    @GetMapping("/matched")
    public List<User> getMatchedFeed(@RequestParam("userId") String userId) {
        System.out.println("In get match feed");
        return userService.getMutualMatches(userId);
    }

    @GetMapping("/addedtomatch")
    public List<String> getRightSwiped(@RequestParam("userId") String userId){
        System.out.println("In rightSwiped");
        return userService.getMatches(userId);

    }
}
