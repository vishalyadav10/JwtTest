package com.amazonclone.test.controller;

import com.amazonclone.test.model.Product;
import com.amazonclone.test.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class ProductContoller {
    ProductService productService;
    @GetMapping("/user/product")
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> products =  productService.getProductRepo();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/user/productById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product =  productService.getProductRepoById(id);
        return ResponseEntity.ok().body(product);
    }
    @PostMapping("/admin/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
      Product product1 =  productService.addProduct(product);
      return ResponseEntity.ok().body(product1);
    }
}
