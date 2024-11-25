package com.tanvir.easyMart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String productDescription;
    private Integer productQuantity;
    private Double productPrice;
    private double specialPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

