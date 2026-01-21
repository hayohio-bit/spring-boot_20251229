package com.example.shop.controller;

import com.example.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // ============= 주문 생성 =============
    @PostMapping
    public String createOrder(
            @RequestParam Long memberId,
            @RequestParam Long itemId,
            @RequestParam int count) {

        try {
            Long orderId = orderService.createOrder(memberId, itemId, count);
            return "redirect:/orders/" + orderId;
        } catch (IllegalArgumentException e) {
            return "redirect:/items?error=" + e.getMessage();
        }
    }

    // ============= 주문 상세 =============


    // ============= 회원의 주문 목록 =============


    // ============= 주문 취소 =============


}
