package com.example.myapp.service;

import com.example.myapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service        // 서비스 레이어 빈으로 등록
@Transactional  // 기본적으로 메서드들을 트랜잭션 안에서 실행
public class UserService {

    private final UserRepository userRepository;
    // User 리포지토리 의존성

    //
}
