package com.example.myapp.controller;

import com.example.myapp.domain.User;
import com.example.myapp.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController                     // JSON 기반 REST 컨트롤러
@RequestMapping("/api/users")    // 공통 URL prefix
public class UserController {

    private final UserService userService;  // 서비스 의존성

    public UserController(UserService userService){
        this.userService = userService;
    }

    // ==== 1. 회원 생성 ====
    @PostMapping    // POST /api/users
    public User create(@RequestBody User user){
        return userService.create(user);
        // JSON -> User -> 저장 후 저장된 User 반환
    }

    // ==== 2. 회원 단건 조회 ====
    @GetMapping("/{id}")    // GET /api/users/{id}
    public User get(@PathVariable Long id){
        return userService.get(id);
        // id로 단건 조회
    }


}
