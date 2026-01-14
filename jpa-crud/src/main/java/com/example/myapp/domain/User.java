package com.example.myapp.domain;

import jakarta.persistence.Entity;
// 이 클래스를 JPA 엔티티로 표시하기 위한 애노테이션
import jakarta.persistence.GeneratedValue;
// PK 값 자동 생성 설정
import jakarta.persistence.GenerationType;
// PK 생성 전략(IDENTITY, SEQUENCE 등)을 정의하는 열거형
import jakarta.persistence.Id;

@Entity
// 이 클래스를 DB 테이블과 매핑되는 JPA 엔티티로 지정
public class User {

    @Id
    // 아래 필드가 User 엔티티의 기본 키임을 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // DB의 AUTO_INCREMENT 기능을 사용하는 PK 자동 생성 전략 설정
    public Long id;
    // 각 User 레코

}
