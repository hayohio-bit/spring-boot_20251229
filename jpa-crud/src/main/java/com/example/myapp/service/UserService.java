package com.example.myapp.service;

import com.example.myapp.domain.User;
import com.example.myapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service        // 서비스 레이어 빈으로 등록
@Transactional  // 기본적으로 메서드들을 트랜잭션 안에서 실행
public class UserService {

    private final UserRepository userRepository;
    // User 리포지토리 의존성

    // 생성자 주입 (스프링이 UserRepository 구현체를 주입해 줌)
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // ==== 1. 회원 생성 ====
    public User create(User user){
        // JPA가 user를 영속성 컨텍스트에 저장하고, DB에 INSERT 수행
        return userRepository.save(user);
    }

    // ==== 2. 회원 단건 조회 ====
    @Transactional(readOnly = true) // 조회 성능 최적화 용도
    public User get(Long id){
        // id로 User 조회, 없으면 예외 던지기
        return userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("User not found. id=" + id));
    }

    // ==== 3. 회원 수정 ====
    public User update(Long id, String username, String password, String email){
        
    }
}
