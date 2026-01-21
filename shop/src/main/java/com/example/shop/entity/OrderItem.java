package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@ToString(exclude = {"order", "item"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    // 어느 주문에 포함되어 있는지 : 양방향 관계 (Order ← OrderItem)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Order order;

    // 어떤 상품인지 : Lazy Loading (Item은 별도로 조회)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    // 주문 당시 상품 가격
    @Column(nullable = false)
    private int orderPrice;

    // 구매 수량
    @Column(nullable = false)
    private int count;

    // ============== 팩토리 메서드 ==============
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        // 상품 재고 감소
        item.removeStock(count);

        return orderItem;
    }

    // ============== 총 가격 계산 ==============
    public int getTotalPrice() {
        return orderPrice * count;
    }

    // ============== 주문 취소 시 ==============
    public void cancel() {
        // 재고 복구
        item.addStock(count);
    }

}
