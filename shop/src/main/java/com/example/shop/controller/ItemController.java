package com.example.shop.controller;

import com.example.shop.entity.Item;
import com.example.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    // ========== 상품 목록 페이지 ==========
    // GET /items?page=0&size=10
    @GetMapping
    public String itemList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Item> items = itemService.findItemsPage(pageable);

        model.addAttribute("items", items);

        return "items/itemList";
    }

    // ========== 상품 상세 페이지 ==========
    // GET /items/1
    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable Long itemId, Model model) {
        Item item = itemService.findItem(itemId);
        model.addAttribute("item", item);

        return "items/itemDetail";
    }

    // ========== 상품 검색 ==========
    // GET /items/search?keyword=노트북
    @GetMapping("/search")
    public String searchItem(@RequestParam String keyword, Model model) {
        var items = itemService.searchByItemNm(keyword);
        model.addAttribute("items", items);
        model.addAttribute("keyword", keyword);

        return "items/itemList";
    }

}
