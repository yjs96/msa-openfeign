package com.example.inventoryservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    private final Random random = new Random();

    @GetMapping("/{productId}")
    public ResponseEntity<Boolean> checkStock(@PathVariable String productId) {
        log.info("정보: INVEN: Check stock for productId {}", productId);
        try {
            int delay = random.nextInt(5000);
            log.info("정보: Checking stock for product {} with delay {}ms", productId, delay);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (random.nextInt(100) < 80) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
