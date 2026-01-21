package com.example.shop.service;

import com.example.shop.entity.Category;
import com.example.shop.entity.Item;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    // =============== 상품 저장 ===============
    @Transactional
    public Long saveItem(String itemNm, int price, int stockNumber, String itemDetail, Long categoryId){

        // 카테고리 조회
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        // 상품 생성 후 저장
        Item item = Item.createItem(itemNm, price, stockNumber, itemDetail, category);
        itemRepository.save(item);

        return item.getId();
    }

    // =============== 상품 조회 (ID) ===============
    public Item findItem(Long itemId){
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
    }

    // =============== 전체 상품 조회 ===============
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    // =============== 페이징 조회 ===============
    public Page<Item> findItemsPage(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    // =============== 상품명으로 검색 ===============
    public List<Item> searchByItemNm(String itemNm){
        return itemRepository.findByItemNm(itemNm);
    }

    // =============== 상품 정보 수정 ===============
    @Transactional
    public void updateItem(Long itemId, String itemNm, int price, int stockNumber, String itemDetail){
        Item item = findItem(itemId);
        item.updateItem(itemNm, price, stockNumber, itemDetail);
    }

    // =============== 상품 삭제 ===============
    @Transactional
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

}
