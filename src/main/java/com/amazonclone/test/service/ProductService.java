package com.amazonclone.test.service;

import com.amazonclone.test.model.Product;
import com.amazonclone.test.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public List<Product>  getProductRepo() {
        return productRepo.findAll();
    }
}
