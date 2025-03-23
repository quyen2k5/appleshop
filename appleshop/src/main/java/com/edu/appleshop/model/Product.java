package com.edu.appleshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Products")
public class Product {
    @Id
    @Column(name = "ProductID")
    private String productID;

    @Column(name = "Image")
    private String image;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Category")
    private String category;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "Status")
    private String status;

    
}
