package com.example.shop.entity;

import com.example.shop.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")     // "order"는 SQL 예약어이므로 "orders" 사용
@Getter
@Setter
@ToString(exclude = {"member", "orderItems"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    // 주문한 회원 : Lazy Loading + 외래키 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus = OrderStatus.ORDER;


    // 이 주문에 포함된 상품들 : 일대다 양방향 관계 + 영속성 전이
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(nullable = false)
    private int totalPrice = 0;

    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updateDate = LocalDateTime.now();

    // =========== 팩토리 메서드 (정적) ===========
    public static Order createOrder(Member member, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setMember(member);

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }

    // =========== 주문항목 추가 ===========
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // =========== 총 주문 가격 ===========
    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

    // =========== 주문 취소 ===========
    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;

        // 모든 주문항목 취소 (재고 복구)
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

}
