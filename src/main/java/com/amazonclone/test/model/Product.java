package com.amazonclone.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    private String name; 
    private String description; 
    private Double price; 
    private Integer stock;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
