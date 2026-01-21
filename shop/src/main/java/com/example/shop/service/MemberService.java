package com.example.shop.service;

import com.example.shop.entity.Member;
import com.example.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    // ============ 회원가입 ============
    @Transactional
    public Long saveMember(String email, String password, String name, String address) {

        // 중복 체크
        Member existMember = memberRepository.findByEmail(email);
        if (existMember != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        // 회원 생성 및 저장
        Member member = Member.createMember(email, password, name, address);
        memberRepository.save(member);

        return member.getId();
    }

    // ============ 회원 조회 ============
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
    }

    // ============ 이메일로 회원 검색 ============
    public Member findByEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        if(member == null) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
        }
        return member;
    }

    // ============ 회원 정보 수정 ============
    @Transactional
    public void updateMember(Long memberId, String password, String name, String address) {
        Member member = findMember(memberId);
        member.updateMember(password, name, address);
    }

}
