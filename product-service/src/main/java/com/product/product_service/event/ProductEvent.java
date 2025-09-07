package com.product.product_service.event;

import com.product.product_service.entity.Product;
import org.springframework.context.ApplicationEvent;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductEvent  {

    private Product product;
    private String eventType;


}
