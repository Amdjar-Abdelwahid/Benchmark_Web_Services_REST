package com.example.serviceD.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private String name;
    private double price;
    private int stock;

    @Column(name = "updated_at")
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

