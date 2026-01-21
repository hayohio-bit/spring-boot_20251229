package com.example.shop.controller;

import com.example.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // ============ 회원 가입 페이지 ============
    @GetMapping("/new")
    public String memberForm() {
        return "members/memberForm";
    }

    // ============ 회원 가입 처리 ============
    @PostMapping("/new")
    public String createMember(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String address) {

        try {
            memberService.saveMember(email, password, name, address);
            return "redirect:/";    // 가입 성공 시 홈으로 이동
        } catch (IllegalArgumentException e) {
            return "redirect:/members/new?error=" + e.getMessage();
        }
    }
}
