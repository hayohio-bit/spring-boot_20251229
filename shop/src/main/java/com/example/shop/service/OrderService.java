package com.example.shop.service;

import com.example.shop.entity.Item;
import com.example.shop.entity.Member;
import com.example.shop.entity.Order;
import com.example.shop.entity.OrderItem;
import com.example.shop.repository.ItemRepository;
import com.example.shop.repository.MemberRepository;
import com.example.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 이 클래스의 모든 메서드는 읽기 전용 트랜잭션으로 동작하게 설정
public class OrderService {

    // 의존성 주입 : 주문 관련 DB 작업을 수행하는 리포지토리
    private final OrderRepository orderRepository;
    // 회원 정보 조회를 위한 리포지토리
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    // =========== 주문 생성 ===========
    @Transactional
    public Long createOrder(Long memberId, Long itemId, int count) {

        // 1. 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        // 2. 상품 조회
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다"));

        // 3. 주문항목 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 4. 주문 생성
        Order order = Order.createOrder(member, List.of(orderItem));

        // 5. DB에 저장
        orderRepository.save(order);

        return order.getId();
    }

    // =========== 주문 조회 ===========
    public Order findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
    }

    // =========== 회원별 주문 조회 ===========
    public List<Order> findOrdersByMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        return orderRepository.findByMember(member);
    }

    // =========== 주문 취소 ===========
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = findOrder(orderId);
        order.cancelOrder();
    }

}
