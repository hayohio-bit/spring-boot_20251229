package com.example.myapp.repository;

import com.example.myapp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// User 엔티티를 DB에 CRUD 할 수 있게 해주는 JPA 리포지토리 인터페이스
public interface UserRepository extends JpaRepository<User, Long> {
// <User, Long> : PK가 Long인 User 엔티티에 대한 표준 CRUD + 페이징 메서드를 가진 리포지토리
}

/*
public interface UserRepository extends JpaRepository<User, Long>
이 한 줄로 아래 메서드들이 자동으로 제공됨
        User save(User user)

        Optional<User> findById(Long id)

        List<User> findAll()

        Page<User> findAll(Pageable pageable) ← 페이징

        void deleteById(Long id)
            */
