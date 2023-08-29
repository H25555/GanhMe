package com.example.sheepopfood.service.request;

import com.example.sheepopfood.model.Category;
import com.example.sheepopfood.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaveRequest {
    private MultipartFile image;
    private String name;
    private BigDecimal price;
    private String description;
    private List<Category> categories;

}
