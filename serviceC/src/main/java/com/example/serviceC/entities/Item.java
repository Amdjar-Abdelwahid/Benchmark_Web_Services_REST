package com.example.serviceC.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private String name;
    private double price;
    private int stock;

    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getId() { return id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public Category getCategory() { return category; }

    public void setId(Long id) { this.id = id; }
    public void setSku(String sku) { this.sku = sku; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setCategory(Category category) { this.category = category; }
}

