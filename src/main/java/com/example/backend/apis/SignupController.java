package com.example.backend.apis;

import com.example.backend.models.User;
import com.example.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        System.out.println(user);
        if(!userService.signUp(user)){
            return ResponseEntity.ok("User already signed up");
        }
        return ResponseEntity.ok("User signed up successfully");
    }
}



