package com.example.myapp.controller;

import com.example.myapp.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                     // JSON 기반 REST 컨트롤러
@RequestMapping("/api/users")    // 공통 URL prefix
public class UserController {

    private final UserService userService;  // 서비스 의존성

    public UserController(UserService userService){
        
    }


}
