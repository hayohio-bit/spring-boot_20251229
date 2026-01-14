package com.example.jpa.service;

import com.example.jpa.domain.Member;
import com.example.jpa.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Log4j2
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자 주입
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 수정
    public void update(Member member){
        memberRepository.save(member);
    }

    // 추가
    public void insert(Member member){
        memberRepository.save(member);
    }

    // 삭제
    public void delete(int memberId){
        memberRepository.deleteById(memberId);
    }

    // 단건 조회
    public Member findById(int memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("해당 회원이 없습니다."));
    }

    // 전체데이터 조회 + 페이징
    public Page<Member> findByAll(Pageable pageable){
        log.info("--------findByAll-----------");
        log.info("pageable : {}", pageable);

        Page<Member> memberPage = memberRepository.findAll(pageable);

        log.info("memberPage : {}", memberPage);
        log.info("---------------end findByAll---------");
        return memberPage;
    }




}
