package com.example.shop.controller;

import com.example.shop.entity.Order;
import com.example.shop.repository.MemberRepository;
import com.example.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberRepository memberRepository;

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
    @GetMapping("/{orderId}")
    public String orderDetail(@PathVariable Long orderId, Model model) {
        Order order = orderService.findOrder(orderId);
        model.addAttribute("order", order);

        return "orders/orderDetail";
    }

    // ============= 회원의 주문 목록 =============
    @GetMapping
    public String orderList(@RequestParam Long memberId, Model model) {
        List<Order> orders = orderService.findOrdersByMember(memberId);
        model.addAttribute("orders", orders);

        return "orders/orderList";
    }

    // ============= 주문 취소 =============
    @PostMapping("{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders/" + orderId;
    }

}
