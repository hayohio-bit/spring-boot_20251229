package com.example.shop.dto;

import com.example.shop.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderItemDto {

    // 주문한 상품의 ID : 어떤 상품을 가리키는지 식별하기 위한 값
    private Long itemId;

    // 주문한 상품 이름 : 화면에 "상품명" 컬럼에 보여주는 값
    private String itemName;

    // 주문 당시 상품 가격(단가) OrderItem.orderPrice
    // 상품 가격이 바뀌어도, 이 값은 주문 시점의 가격을 유지
    private int itemPrice;

    private int count;

    // 주문 상품 한 줄의 총 금액 (itemPrice * count)
    // 화면에서 다시 계산하지 않고 서버에서 계산된 값을 그대로 사용하기 위한 필드
    private int totalPrice;


    // 엔티티 OrderItem을 받아서, DTO로 변환하는 생성자
    // 서비스 계층에서 OrderItem 리스트를 조회한 뒤
    // 각 요소를 new OrderItemDto(orderItem)으로 감싸서
    // List<OrderItemDto> 로 만들어 컨트롤러/뷰에 넘길 때 사용
    public OrderItemDto(OrderItem orderItem) {
        // 주문 상품이 참조하고 있는 실제 Item 엔티티의 ID
        this.itemId = orderItem.getItem().getId();

        this.itemName = orderItem.getItem().getItemNm();;

        // 주문 당시 가격 : OrderItem이 가지고 있는 orderPrice 사용
        this.itemPrice = orderItem.getOrderPrice();

        // 주문 수량 : OrderItem이 가진 count 사용
        this.count = orderItem.getCount();

        // 총 금액 : OrderItem의 비즈니스 로직 메서드 getTotalPrice() 사용
        // OrderItem 내부 구현(가격*수량)이 바뀌어도 이 메서드를 통해 일관성 유지
        this.totalPrice = orderItem.getTotalPrice();
    }

}
