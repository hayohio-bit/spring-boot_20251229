package com.example.shop.dto;

import com.example.shop.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 주문 정보를 전송하기 위한 DTO 클래스
// Entity인 Order를 직접 노출하지 않고 DTO로 변환하여 API 응답이나 뷰로 전달함
@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDto {

    // 주문 고유 ID
    private Long orderId;

    private String memberEmail;

    private String memberName;

    private String orderStatus;

    private int totalPrice;

    private LocalDateTime orderDate;

    private LocalDateTime updateDate;

    // 주문에 포함된 주문 상품 목록 (OrderItemDto 리스트)
    private List<OrderItemDto> orderItems;


    // Entity Order 객체를 OrderDto로 변환하는 생성자
    // 주문 엔티티의 필드들을 DTO로 매핑하며, 연관된 orderItems를 OrderItemDto 리스트로 변환
    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.memberEmail = order.getMember().getEmail();
        this.memberName = order.getMember().getName();
        // enum을 문자열로 변환
        this.orderStatus = order.getOrderStatus().name();
        this.totalPrice = order.getTotalPrice();
        this.orderDate = order.getOrderDate();
        this.updateDate = order.getUpdateDate();

        // orderItems를 Stream으로 변환 : 각 OrderItem을 OrderItemDto로 매핑 후 리스트 수집
        this.orderItems = order.getOrderItems().stream()
                .map(OrderItemDto::new)
                // OrderItem -> OrderItemDto 변환 (OrderItemDto 생성자 호출)
                .collect(Collectors.toList());
                // List<OrderItemDto>로 수집
    }
}
