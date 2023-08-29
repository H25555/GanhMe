package com.example.sheepopfood.service.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEditRequest {
    private int id;
    private String name;
    private BigDecimal price;
    private String description;
}
