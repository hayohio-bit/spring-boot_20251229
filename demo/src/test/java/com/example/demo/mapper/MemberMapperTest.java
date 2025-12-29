package com.example.demo.mapper;

import com.example.demo.domain.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    @DisplayName("ALL READ: 전체 데이터 조회")
    void findAll() {
        // Given-When-Then : 테스트 구조를 명확히 하는 BDD 패턴
        // given: 테스트 전제 조건 준비 (예: 더미 데이터 삽입)
        // when: 실제 테스트 대상 메서드 호출 (여기서는 memberMapper.findAll())
        List<MemberDTO> list = memberMapper.findAll();

        // then: 결과 검증 (예: 반환된 회원 목록 크기 확인)
        Assertions.assertNotNull(list);     //null만 아니면 OK

        list.forEach(log::info);
    }

    @Test
    @DisplayName("CREATE: 회원 가입")
    void testInsert() {
        // Given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("홍길동")
                .age(100)
                .address("길동")
                .phone("010-5555-6666")
                .build();
        // When
        int result = memberMapper.insert(memberDTO);

        // Then
        Assertions.assertEquals(1,result);
        Assertions.assertTrue(memberDTO.getMemberId()>0);
    }

    @Test
    @DisplayName("FIND ONE: ID로 회원 조회")
    void testFindById() {
        // Given
        int memberId = 1;
        // When
        MemberDTO memberDTO = memberMapper.findById(memberId);
        // Then
        Assertions.assertNotNull(memberDTO);
        log.info(memberDTO);
    }

    @Test
    @DisplayName("UPDATE: 회원 정보 수정")
    void testUpdate(){
        // Given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("청길동")
                .age(10)
                .address("청동")
                .phone("010-6666-5555")
                .build();
        // When
        memberMapper.insert(memberDTO);
        int memberId = memberDTO.getMemberId();

        // Then
        List<MemberDTO> memberDTOList;
    }

}