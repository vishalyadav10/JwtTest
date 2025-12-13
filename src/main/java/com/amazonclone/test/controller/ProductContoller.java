package com.amazonclone.test.controller;

import com.amazonclone.test.model.Category;
import com.amazonclone.test.model.Product;
import com.amazonclone.test.repository.CategoryRepo;
import com.amazonclone.test.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class ProductContoller {
    ProductRepo productRepo;
    CategoryRepo categoryRepo;
    //add one of search by keyword
    //AuthenticationPrincipal
    @GetMapping("/user/product")
    public ResponseEntity<List<Product>> getProductList( @AuthenticationPrincipal UserDetails user) {
        String username = user.getUsername();
        System.out.println("User fetched product list: " + username);
        List<Product> products =  productRepo.findAll();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/user/productById/{id}")
    public ResponseEntity<Product> getProductById( @AuthenticationPrincipal UserDetails user,@PathVariable Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return ResponseEntity.ok().body(product);
    }
    @PostMapping("/admin/addProduct")
    public ResponseEntity<Product> addProduct( @AuthenticationPrincipal UserDetails user, @RequestBody Product product) {
        String adminUser = user.getUsername();            // Optional: store/admin auditing
        System.out.println("Admin adding product: " + adminUser);
        Long categoryId = product.getCategory().getId();
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        Product product1 = productRepo.save(product);
      return ResponseEntity.ok().body(product1);
    }
}
