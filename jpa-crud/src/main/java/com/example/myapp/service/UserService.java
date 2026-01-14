package com.example.myapp.service;

import com.example.myapp.domain.User;
import com.example.myapp.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        // 1) 수정 대상 엔티티를 먼저 조회 (영속 상태)
        User user = get(id);

        // 2) 엔티티의 도메인 메서드로 상태 변경
        user.changeUsername(username);
        user.changePassword(password);
        user.changeEmail(email);

        // 3) @Transactional 덕분에 메서드가 끝날 때 변경 내용이 자동으로 DB에 반영(더티 체킹)
        return user;
    }

    // ==== 4. 회원 삭제 ====
    public void delete(Long id){
        userRepository.deleteById(id); // 해당 id의 User를 DB에서 삭제
    }
    
    // ==== 5. 회원 목록 + 페이징 조회 ====
    @Transactional(readOnly = true)
    public Page<User> getList(Pageable pageable){
        // page, size, sort 정보가 담긴 Pageable을 사용해 페이징 조회
        return userRepository.findAll(pageable);
    }
}
