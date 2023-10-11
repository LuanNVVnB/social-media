package com.example.cnd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public ResponseEntity<Object> getHello() {
        return ResponseEntity.status(200)
                .body("hello");
    }
}
