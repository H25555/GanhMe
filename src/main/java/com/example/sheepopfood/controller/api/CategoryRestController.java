package com.example.sheepopfood.controller.api;

import com.example.sheepopfood.model.Category;
import com.example.sheepopfood.service.CategoryService;
import com.example.sheepopfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/category")
public class CategoryRestController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

}
