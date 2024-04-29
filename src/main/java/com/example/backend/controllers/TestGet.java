package com.example.backend.controllers;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestGet {

    @GetMapping("/hello")
    public String test(){
        return "Welcome to LoveLyne";
    }
}
