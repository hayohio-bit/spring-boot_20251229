// 주문 상태 열거형
package com.example.shop.constant;

// 주문의 생명주기(Lifecycle)를 나타내는 상태
// 주문이 생성 및 배송, 완료될 때까지의 모든 과정을 표현
public enum OrderStatus {

    ORDER,      // 주문됨
    CANCEL,     // 취소됨
    DELIVERY    // 배송됨

}
