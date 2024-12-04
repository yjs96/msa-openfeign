package com.example.inventoryservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {
    @GetMapping("/{productId}")
    public boolean checkStock(@PathVariable String productId) {
        log.info("정보: INVEN: Check stock for productId {}", productId);
        return true;
    }
}
