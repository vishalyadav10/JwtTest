package com.amazonclone.test.controller;

import com.amazonclone.test.model.Product;
import com.amazonclone.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductContoller {
    @Autowired
    ProductService productService;
    @GetMapping("/public/path")
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> products =  productService.getProductRepo();
        return ResponseEntity.ok(products);
    }
}
