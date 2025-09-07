package com.product.product_service.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductDTO {
    private String name;
    private double price;
    private int stock;
}
