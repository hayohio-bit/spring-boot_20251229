package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@ToString
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    // 어떤 상품인지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // 어느 주문에 포함되어 있는지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Order order;

    // 구매 당시의 가격
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
