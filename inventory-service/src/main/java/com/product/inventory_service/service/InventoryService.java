package com.product.inventory_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.inventory_service.entity.Inventory;
import com.product.inventory_service.repository.InventoryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public boolean checkStock(Long productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        return inventory != null && inventory.getStock() >= quantity;
    }

    public void reduceStock(Long productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory != null && inventory.getStock() >= quantity) {
            inventory.setStock(inventory.getStock() - quantity);
            inventoryRepository.save(inventory);
        }
    }
    @KafkaListener(topics = "product-events", groupId = "inventory-group")
    public void handleProductEvent(String message) {
        System.out.println("Received Kafka message: " + message); // ✅ Debugging log

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(message);

            System.out.println("Parsed JSON: " + jsonNode.toString()); // ✅ Debugging log

            // Validate required fields exist
            if (!jsonNode.has("eventType") || !jsonNode.has("product")) {
                System.err.println("❌ Missing required fields: " + jsonNode.toString());
                return;
            }

            String eventType = jsonNode.get("eventType").asText();
            JsonNode productNode = jsonNode.get("product"); // Extract the nested product object

            if (productNode == null || !productNode.has("id") || !productNode.has("name")) {
                System.err.println("❌ Missing product details in event: " + jsonNode.toString());
                return;
            }

            if ("PRODUCT_CREATED".equals(eventType)) {
                Long productId = productNode.get("id").asLong();
                String productName = productNode.get("name").asText();

                System.out.println("✅ Initializing stock for: " + productName);

                // Add stock to inventory
                Inventory inventory = new Inventory();
                inventory.setProductId(productId);
                inventory.setStock(100);  // Default stock
                inventoryRepository.save(inventory);
            }
        } catch (Exception e) {
            System.err.println("❌ Error processing product event: " + e.getMessage());
        }
    }



}


