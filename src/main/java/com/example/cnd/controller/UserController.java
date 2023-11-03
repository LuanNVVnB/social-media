package com.example.cnd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * File: com.example.cnd.controller
 * Description:  <div style="font-weight: bold; color: #0073e6;"> UserCon troller </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;">LuanNVV</span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public ResponseEntity<Object> getHello() {
        return ResponseEntity.status(200)
                .body("hello");
    }
}
