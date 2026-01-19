package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "category")
// Lombok 라이브러리 : 자동으로 get/set 메서드 생성
@Getter
@Setter
@ToString   // toString() 메서드 자동 생성 (디버깅용)
public class Category {

    // 기본키(Primary key) : 각 행을 유일하게 식별
    @Id
    // IDENTITY : 자동으로 1, 2, 3, ··· 증가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false, unique = true)
    // nullable = false : NULL 값 불허
    // unique = true : 중복 금지 (같은 카테고리명 불가)
    private String cateName;

    // 기본 생성자 (JPA 규칙)
    public Category() {}

    // 매개변수 있는 생성자 (카테고리명으로 바로 생성)
    public Category(String cateName){
        this.cateName = cateName;
    }
}
