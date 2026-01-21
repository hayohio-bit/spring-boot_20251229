package com.example.shop.service;

import com.example.shop.entity.Category;
import com.example.shop.entity.Item;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Long categoryId;

    @BeforeEach
    void setUp(){
        // 테스트용 카테고리 생성
        Category category = new Category("전자제품");
        categoryRepository.save(category);
        categoryId = category.getId();
    }

    @Test
    void 상품저장_성공() {
        // when
        Long itemId = itemService.saveItem("노트북", 1500000, 10, "고사양 노트북", categoryId);

        // then
        Item item = itemService.findItem(itemId);
        assertEquals("노트북", item.getItemNm());
        assertEquals(1500000, item.getPrice());
    }

    @Test
    void 상품조회_성공() {
        // given
        Long itemId = itemService.saveItem("마우스", 50000, 50, "무선마우스", categoryId);

        // when
        Item item = itemService.findItem(itemId);

        // then
        assertNotNull(item);
        assertEquals("마우스", item.getItemNm());
    }

    @Test
    void 상품수정_성공() {
        // given
        Long itemId = itemService.saveItem("키보드", 100000, 20, "기계식 키보드", categoryId);

        // when
        itemService.updateItem(itemId, "기계식 키보드", 120000, 15, "RGB LED 기계식 키보드");

        // then
        Item item = itemService.findItem(itemId);
        assertEquals("기계식 키보드", item.getItemNm());
        assertEquals(120000, item.getPrice());
    }

    @Test
    void 재고감소_성공() {
        // given
        Long itemId = itemService.saveItem("모니터", 300000, 5, "27인치 모니터", categoryId);
        Item item = itemService.findItem(itemId);

        // when
        item.removeStock(2);

        // then
        assertEquals(3, item.getStockNumber());
    }

    @Test
    void 재고감소_실패_재고부족() {
        // given
        Long itemId = itemService.saveItem("키보드", 100000, 2, "기계식 키보드", categoryId);
        Item item = itemService.findItem(itemId);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            item.removeStock(5);    // 재고 2개인데 6개 구매 시도
        });
    }

}