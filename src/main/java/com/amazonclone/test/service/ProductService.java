package com.amazonclone.test.service;

import com.amazonclone.test.model.Category;
import com.amazonclone.test.model.Product;
import com.amazonclone.test.repository.CategoryRepo;
import com.amazonclone.test.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    ProductRepo productRepo;
    CategoryRepo categoryRepo;
    public List<Product>  getProductRepo() {
        return productRepo.findAll();
    }
    public Product getProductRepoById(Long id){
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
    public Product addProduct(Product product) {
        String categoryName = product.getCategory().getName();
        Category category = categoryRepo.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        productRepo.save(product);
        return product;
    }
}
