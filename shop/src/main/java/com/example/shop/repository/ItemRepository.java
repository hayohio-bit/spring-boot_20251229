package com.example.shop.repository;

import com.example.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // 1. 단순 조회 (Query Method)
    List<Item> findByItemNm(String itemNm);

    // 2. 복합 조건 (OR)
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // 3. JPQL 사용 (권장)
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    // 4. Native SQL
    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc",
            nativeQuery = true)
    List<Item> findByItemByNative(@Param("itemDetail") String itemDetail);
}