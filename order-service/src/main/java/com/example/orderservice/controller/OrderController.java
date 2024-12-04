package com.example.orderservice.controller;

import com.example.orderservice.client.InventoryClient;
import com.example.orderservice.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String createOrder(@RequestBody OrderDto orderDto) {
        log.info("정보: ORDER: Received Order Request: {}", orderDto);

        log.info("정보: ORDER: Checking Stock productId: {}", orderDto.getProductId());
        boolean hasStock = inventoryClient.checkStock(orderDto.getProductId());
        if (hasStock) {
            log.info("정보: ORDER: Stock Available, Creating...");
            return "Order Created Successfully";
        }
        return "No Stock Available";
    }
}
