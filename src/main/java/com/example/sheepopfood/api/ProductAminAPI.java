package com.example.sheepopfood.api;

import com.example.sheepopfood.model.Category;
import com.example.sheepopfood.model.Product;
import com.example.sheepopfood.model.User;
import com.example.sheepopfood.service.CategoryService;
import com.example.sheepopfood.service.ProductService;
import com.example.sheepopfood.service.UserService;
import com.example.sheepopfood.service.request.ProductEditRequest;
import com.example.sheepopfood.service.request.ProductSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class ProductAminAPI {
    @Autowired
    private ResourceLoader resourceLoader;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    public ProductAminAPI(ProductService productService, CategoryService categoryService, UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {


        List<Product> products = productService.fillAll();


        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/create")
    @ResponseBody
    public ResponseEntity<List<Category>> showCreate() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> createPro(@ModelAttribute ProductSaveRequest productSaveRequest) throws IOException {
        String name = productSaveRequest.getName();
        BigDecimal price = productSaveRequest.getPrice();
        String description = productSaveRequest.getDescription();
        List<Category> categories = productSaveRequest.getCategories();
        MultipartFile imgFile = productSaveRequest.getImage();
        if (imgFile.isEmpty()) {
            return ResponseEntity.badRequest().body("Video and image files are required.");
        }

        if (name.isEmpty() || categories.isEmpty()) {
            return ResponseEntity.badRequest().body("Name and category are required.");
        }
        long userId = userService.getUser().getId();
        Resource resource = resourceLoader.getResource("classpath:/src/main/resources/static/assets/img/product_images");
        String templatePath = resource.getFile().getAbsolutePath();
        Path p = Paths.get(templatePath + File.separator + imgFile.getOriginalFilename());
        InputStream inputStream = imgFile.getInputStream();
        Files.copy(inputStream, p, StandardCopyOption.REPLACE_EXISTING);

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(p.toString().split("assets")[1]);
        product.setUser(new User(userId));
        product.setCategory(categories.get(0));
        productService.Create(product);
        return ResponseEntity.ok("Product created successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductForEdit(@PathVariable int id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    @PatchMapping ("/{productId}")
    @ResponseBody
    public ResponseEntity<?> editPro(@ModelAttribute ProductEditRequest productEditRequest) throws IOException {

        int productId = productEditRequest.getId();
        String name = productEditRequest.getName();
        BigDecimal price = productEditRequest.getPrice();
        String description = productEditRequest.getDescription();

        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.badRequest().body("Product not found.");
        }

        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);

        productService.update(product);

        return ResponseEntity.ok("Product updated successfully.");
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully.");
    }
    @GetMapping("/find")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String search) {
        List<Product> productList = productService.findByNameContainingOrCategoryName(search,search);
        return ResponseEntity.ok(productList);
    }
}


