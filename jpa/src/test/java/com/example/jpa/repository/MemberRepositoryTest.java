package com.example.jpa.repository;

import com.example.jpa.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("추가")
    public void insetTest(){
        Member member = Member.builder()
                .name("다")
                .age(13)
                .phone("010-1111-1111")
                .address("다")
                .build();
        memberRepository.save(member);
    }

    @Test
    @DisplayName("수정")
    public void updateTest() {
        Optional<Member> optMember = memberRepository.findById(4);
        Member member = optMember.get();

        member.setName("길동");
        member.setAge(5);
        member.setAddress("강동구");

        memberRepository.save(member);
    }

    @Test
    @DisplayName("삭제")
    public void deleteTest(){
        memberRepository.deleteById(1);
    }

    @Test
    @DisplayName("전체데이터 조회")
    public void selectAll(){
        List<Member> memberList = memberRepository.findAll();
        memberList.forEach(member -> log.info("member = {}", member));
    }

    @Test
    @DisplayName("조회")
    public void selectTest(){
        //Member member = memberRepository.findMemberByName("까미");
        //Member member =
        //        memberRepository.findByNameAndAddress("길동","강동구");
        List<Member> memberList = memberRepository.findByAgeGreaterThanEqual(10);
        memberList.forEach(member -> log.info("member = {}", member));
        log.info("memberList = {}", memberList);
    }

    @Test
    @DisplayName("조회:나이")
    public void findByAgeOrderByAgeDesc(){
        List<Member> members = memberRepository.findByAgeOrderByAgeDesc(20);
        members.forEach(member -> log.info(member));
    }

    @Test
    void dummyMembers() {
        for (int i = 1; i <= 40; i++) {
            Member m = new Member();
            m.setName("회원" + i);
            m.setAge(20 + (i % 10));
            m.setAddress("서울시 테스트동 " + i + "번지");
            m.setPhone("010-0000-" + String.format("%04d", i));
            memberRepository.save(m);
        }
    }

}