package com.example.shop.entity;

import com.example.shop.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // 이메일 : 중복 불허 (로그인 아이디 대신 사용)
    @Column(nullable = false, unique = true)
    private String email;

    // 비밀번호 : 암호화 필수이나 학습용으로 생략함
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private LocalDateTime regTime = LocalDateTime.now();
    private LocalDateTime updateTime = LocalDateTime.now();

    // ============= 팩토리 메서드 =============
    public static Member createMember(String email, String password, String name, String address){
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setName(name);
        member.setAddress(address);
        member.setRole(Role.USER);
        member.setRegTime(LocalDateTime.now());
        member.setUpdateTime(LocalDateTime.now());
        return member;
    }

    // ============= 비즈니스 로직 =============
    public void updateMember(String password, String name, String address) {
        this.password = password;
        this.name = name;
        this.address = address;
        this.updateTime = LocalDateTime.now();
    }

}

