package com.example.shop.entity;

import com.example.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Entity     // entity로 선언한 클래스는 반드시 기본키(기본키 멤버변수에 @Id 어노테이션)를 가져야 함.
@Table(name = "item")   // 어떤 테이블과 매핑될 지 지정
@Getter
@Setter
@ToString
public class Item {

    @Id // 상품 고유번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    // 상품명 : 50글자 제한, 필수입력
    @Column(nullable = false, length = 50)
    private String itemNm;

    // 가격 : 정수형, 필수입력
    @Column(name = "price", nullable = false)
    private int price;

    // 재고 수량 : 필수입력
    @Column(nullable = false)
    private int stockNumber;

    // @Lob : 긴 텍스트 저장 (큰 용량)
    // 상품 설명처럼 길 수 있는 텍스트용
    @Lob
    @Column(nullable = false)
    private String itemDetail;

    // 상품 판매 상태 ( SELL 또는 SOLD_OUT)
    // @Enumerated(EnumType.STRING) : Enum을 문자열로 DB에 저장
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    // 카테고리와의 관계
    // @ManyToOne : 많은 상품이 하나의 카테고리에 속함
    // fetch = FetchType.LAZY : 필요할 때만 카테고리 정보 로드 (성능최적화)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id") // 외래키(Foreign Key)
    private Category category;

    // 등록 시간 : 상품이 생성된 시간
    private LocalDateTime regTime;

    // 수정 시간 : 상품이 마지막으로 수정된 시간
    private LocalDateTime updateTime;

    // =============== 팩토리 메서드 ===============
    // "상품 생성하기"를 한 번에 처리
    // static : 객체 생성 없이 Item.createItem()으로 호출 가능
    public static Item createItem(String itemNm, int price, int stockNumber, String itemDetail, Category category){

        Item item = new Item();
        item.setItemNm(itemNm);
        item.setPrice(price);
        item.setStockNumber(stockNumber);
        item.setItemDetail(itemDetail);
        item.setItemSellStatus(ItemSellStatus.SELL);    // 초기값: 판매중
        item.setCategory(category);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        return item;
    }

    // =============== 비즈니스 로직 ===============

    // 재고 증가 (반품 시 사용)
    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }

    // 재고 감소 (구매 시 사용)
    // throws : 예외 던지기 ="에러 상황 처리"
    public void removeStock(int stockNumber) {

    }

}
