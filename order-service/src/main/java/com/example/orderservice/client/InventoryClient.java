package com.example.orderservice.client;

import com.example.orderservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", configuration = FeignConfig.class)
public interface InventoryClient {
    @GetMapping("/api/inventory/{productId}")
    boolean checkStock(@PathVariable String productId);
}
