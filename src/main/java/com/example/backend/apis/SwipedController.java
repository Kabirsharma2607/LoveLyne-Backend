package com.example.backend.apis;

import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwipedController {

    @Autowired
    private UserService userService;

    @PostMapping("/swiped")
    public ResponseEntity<String> updateMatchStatus
            (@RequestParam("userId") String userId, @RequestParam("newUser") String newUser,
                                                    @RequestParam("matched") boolean matched) {
        System.out.println("In update");
        if(matched){
            userService.updateMatchStatus(userId, newUser);
        }
        userService.addToSeenList(userId, newUser);
        return ResponseEntity.ok("Match status updated successfully");
    }
}
