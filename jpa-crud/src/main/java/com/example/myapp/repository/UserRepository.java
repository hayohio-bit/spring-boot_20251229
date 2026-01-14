package com.example.myapp.repository;

import com.example.myapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
// User 엔티티를 DB에 CRUD 할 수 있게 해주는 JPA 리포지토리 인터페이스
public interface UserRepository extends JpaRepository<User, Long> {
}
