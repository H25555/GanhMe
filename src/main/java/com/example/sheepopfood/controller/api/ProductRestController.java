package com.example.sheepopfood.controller.api;

import com.example.sheepopfood.model.Category;
import com.example.sheepopfood.model.Product;
import com.example.sheepopfood.service.CategoryService;
import com.example.sheepopfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<?> showProducts(Model model){
        List <Product> productList = productService.findAll();
        model.addAttribute("products",productList);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/create")
    public ResponseEntity<?> showCreate(@ModelAttribute Product product){
        List<Category> categoryList = categoryService.findAll();
        return ResponseEntity.ok(categoryList);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute Product product){
        Category category = categoryService.findById(product.getCategory().getId()).get();
        product.setCategory(category);
        Product productCreate = productService.Create(product);
        return ResponseEntity.ok(productCreate);
    }

    @GetMapping("/edit")
    public ResponseEntity<?> showEdit(@RequestParam Integer id){
        Product product = productService.findById(id);
        List<Category> categoryList = categoryService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("product", product);
        map.put("category", categoryList);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@ModelAttribute Product product){
        productService.update(product);
        return ResponseEntity.ok("thanh cong");
    }



    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("da xoa");
    }



//    @GetMapping("/findName")
//    public ResponseEntity<?> findAndSort(@RequestParam(defaultValue = "@") String price,
//                                         @RequestParam(defaultValue = "")String name){
//        List<Product> productList = productService.findProductsByNameContainingOrderByPriceAsc(name, price);
//        return ResponseEntity.ok(productList);
//    }

    @GetMapping("/category/{id}")

    public ResponseEntity<?> showCategory(@PathVariable int id){
//        Tag tag = tagService.findById(id);
        List<Product> products = productService.findProductByTagId(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategory() {
        List<Category> categoryList = categoryService.findAll();
        return ResponseEntity.ok(categoryList);
    }

}
