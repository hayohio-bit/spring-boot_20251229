package com.example.myapp.controller;

import com.example.myapp.domain.User;
import com.example.myapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
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

    // ==== 3. 회원 수정 ====
    @PutMapping("/{id}")   // PUT /api/users/{id}
    public User update(@PathVariable Long id,
                       @RequestBody User request){
        // 요청으로 들어온 값들로 수정
        return userService.update(
                id,
                request.getUsername(),
                request.getPassword(),
                request.getEmail()
        );
    }

    // ==== 4. 회원 삭제 ====
    @DeleteMapping("/{id}")     // DELETE /api/users/{id}
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    // ==== 5. 회원 목록 + 페이징 ====
    @GetMapping     // GET /api/users?page=0&size=10&sort=id,desc
    public Page<User> list(@PageableDefault(size = 10)Pageable pageable){
        // Pageable은 page/size/sort 정보를 담은 객체
        return userService.getList(pageable);
    }


}
