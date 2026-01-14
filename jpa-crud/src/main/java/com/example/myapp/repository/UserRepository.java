package com.example.myapp.repository;

import com.example.myapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
// User 엔티티를 DB에 CRUD 할 수 있게 해주는 JPA 리포지토리 인터페이스
public interface UserRepository extends JpaRepository<User, Long> {
// <User, Long> : PK가 Long인 User 엔티티에 대한 표준 CRUD + 페이징 메서드를 가진 리포지토리


}
