package com.example.shop.repository;

import com.example.shop.entity.Member;
import com.example.shop.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 특정 회원의 모든 주문 조건 조회
    List<Order> findByMember(Member member);

    // 페이징 처리 (특정 회원의 주문)
    Page<Order> findByMember(Member member, Pageable pageable);
}