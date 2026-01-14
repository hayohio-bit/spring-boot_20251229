package com.example.myapp.repository;

import com.example.myapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
// User 엔티티를 DB에 CRUD 할 수 있게 해주는 JPA 리포지토리 인터페이스
public interface UserRepository extends JpaRepository<User, Long> {
    // 기본 CRUD, 페이징 메서드 들은 JpaRepository가 이미 제공함
    // JpaRepository<User, Long> 에서 User는 domain.User 이고 L
}
