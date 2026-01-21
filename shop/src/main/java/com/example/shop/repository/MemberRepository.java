package com.example.shop.repository;

import com.example.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 회원 검색
    Member findByEmail(String email);

}
