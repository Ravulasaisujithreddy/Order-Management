package com.product.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.product.inventory_service.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProductId(Long productId);
}
