package com.example.orderservice.controller;

import com.example.orderservice.client.InventoryClient;
import com.example.orderservice.dto.OrderDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j // 로그
public class OrderController {
    private final InventoryClient inventoryClient;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) {
        try {
            log.info("Checking stock for product: {}", orderDto.getProductId());
            boolean hasStock = inventoryClient.checkStock(orderDto.getProductId());

            if (hasStock) {
                return ResponseEntity.ok("정보: Order created successfully");
            }
            return ResponseEntity.badRequest().body("정보: No stock available");

        } catch (FeignException e) {
            log.error("Error while checking stock", e);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("정보: Service temporarily unavailable. Please try again.");
        }
    }
}