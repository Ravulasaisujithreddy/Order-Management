package com.product.product_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.product_service.event.ProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.product.product_service.entity.Product;
import com.product.product_service.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product addProduct(Product product) {
        Product savedProduct = repository.save(product);
        ProductEvent event = new ProductEvent(savedProduct, "PRODUCT_CREATED");
        kafkaTemplate.send("product-events", convertToJson(event));
        return savedProduct;
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);  // Returns the product if found, or null if not found
    }

    private String convertToJson(ProductEvent event) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting event to JSON", e);
        }
    }


}
