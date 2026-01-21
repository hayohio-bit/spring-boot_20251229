package com.example.shop.repository;

import com.example.shop.constant.OrderStatus;
import com.example.shop.entity.Member;
import com.example.shop.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 회원의 모든 주문 조회
    List<Order> findByMember(Member member);

    // 회원의 모든 주문 조회 (페이징)
    Page<Order> findByMember(Member member, Pageable pageable);

    // 주문 상태별 조회
    List<Order> findByOrderStatus(OrderStatus orderStatus);

    // 기간별 주문 조회
    @Query("SELECT O FROM Order o WHERE o.orderDate >= :startDate AND o.orderDate <= :endDate")
    List<Order> findOrdersBetween(
            @Param("startDate")LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    // 회원의 최근 주문 조회 (N+1 문제 해결 : fetchJoin)
    @Query("SELECT DISTINCT o FROM Order o " +
            "JOIN FETCH o.member m " +
            "JOIN FETCH o.orderItems oi " +
            "JOIN FETCH oi.item i " +
            "WHERE m.id = :memberId " +
            "ORDER BY o.orderDate DESC")
    List<Order> findByMemberWithItems(@Param("memberId") Long memberId);

    // 주문과 주문 아이템을 함께 조회 (fetchJoin)
    @Query("SELECT DISTINCT o FROM Order o " +
            "JOIN FETCH o.orderItems oi " +
            "JOIN FETCH oi.item " +
            "WHERE o.id = :orderID")
    Order findByIdWithItems(@Param("orderId") Long orderId);
}