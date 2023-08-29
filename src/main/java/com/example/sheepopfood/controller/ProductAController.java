package com.example.sheepopfood.controller;

import com.example.sheepopfood.model.Product;
import com.example.sheepopfood.service.ProductService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductAController {
    private final ProductService productService;

    public ProductAController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showListPage(Model model) {
        List<Product> products = productService.fillAll();
        model.addAttribute("products", products);
        return "productsAmin/list";
    }
}
