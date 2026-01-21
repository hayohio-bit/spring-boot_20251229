package com.example.shop.service;

import com.example.shop.entity.Member;
import com.example.shop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원가입_성공() {
        // when
        Long memberId = memberService.saveMember("user@email.com",
                "password123", "신짱구", "서울시 신짱구");

        // then
        Member member = memberService.findMember(memberId);
        assertEquals("user@email.com", member.getEmail());
        assertEquals("신짱구", member.getName());
    }

    @Test
    void 회원가입_중복_실패() {
        // given
        memberService.saveMember("user@email.com",
                "password123", "신짱구", "서울시 신짱구");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.saveMember("user@email.com",
                    "password456", "철수", "철수집");
        });

    }

    @Test
    void 이메일로회원조회() {
        // given
        memberService.saveMember("test@email.com",
                "password123", "유리", "유리집");

        // when
        Member member = memberService.findByEmail("test@email.com");

        // then
        assertNotNull(member);
        assertEquals("유리", member.getName());

    }


}