package com.example.backend.apis;

import com.example.backend.models.User;
import com.example.backend.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchesController {

    @Autowired
    private UserService userService;

    @GetMapping("/matches")
    @JsonProperty("matches")
    public List<String> getMatches(@RequestParam("userId") String userId) {
        System.out.println(userId);
        return userService.getMatches(userId);
    }
}
