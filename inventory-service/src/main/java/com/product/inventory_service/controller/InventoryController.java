package com.product.inventory_service.controller;

import com.product.inventory_service.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/check")
    public boolean checkStock(@RequestParam Long productId, @RequestParam int quantity) {
        return inventoryService.checkStock(productId, quantity);
    }

    @PostMapping("/reduce")
    public void reduceStock(@RequestParam Long productId, @RequestParam int quantity) {
        inventoryService.reduceStock(productId, quantity);
    }
}
