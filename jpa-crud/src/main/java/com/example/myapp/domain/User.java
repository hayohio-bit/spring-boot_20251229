package com.example.myapp.domain;

import jakarta.persistence.Entity;
// 이 클래스를 JPA 엔티티로 표시
import jakarta.persistence.GeneratedValue;
// PK 값 자동 생성 설정에 사용
import jakarta.persistence.GenerationType;
// PK 생성 전략(IDENTITY, SEQUENCE 등)을 정의하는 열거형
import jakarta.persistence.Id;

@Entity
// 이 클래스를 DB 테이블과 매핑되는 JPA 엔티티로 지정
public class User {

    // 아래 필드가 User 엔티티의 기본 키임을 표시
    @Id
    // DB의 AUTO_INCREMENT 기능을 사용하는 PK 자동 생성 전략 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 각 User 레코드를 유일하게 식별하는 ID 값
    private Long id;

    private String username;
    private String password;
    private String email;

    // JPA가 프록시 생성 시 사용하기 위한 기본 생성자
    // 외부에서 직접 생성하지 못하도록 protected
    protected User(){}

    // 도메인에서 User 생성 시 사용할 생성자 (필수 값만 받도록 설계)
    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // 조회 (getter)
    public Long getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }

    // 변경 메서드 change (단순 값 대입이 아니라 의미 있는 동작으로 만들기 위해 사용)
    // changeXxx -> 규칙을 체크한 뒤 상태를 바꾸는 도메인 행동
    public void changeUsername(String username){
        this.username = username;
    }
    public void changePassword(String password){
        this.password = password;
    }
    public void changeEmail(String email){
        this.email = email;
    }

}
