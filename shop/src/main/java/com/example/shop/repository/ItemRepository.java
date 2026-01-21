package com.example.shop.repository;

import com.example.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // 1. 단순 조회 (Query Method)
    List<Item> findByItemNm(String itemNm);

    // 2. 복합 조건 (OR) : 상품명 또는 상세설명
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // 3. JPQL 사용 (권장)
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    // 4. Native SQL
    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc",
            nativeQuery = true)
    List<Item> findByItemByNative(@Param("itemDetail") String itemDetail);

    // 5. 가격 조건 조회 (Less Than)
    List<Item> findByPriceLessThan(Integer Price);

    // 6. 가격 내림차순 조회 (Order By Price Desc)
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);


    // 페이징 처리 (전체 상품 조회)
    Page<Item> findAll(Pageable pageable);
}