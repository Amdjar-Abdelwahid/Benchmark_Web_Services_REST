package com.example.serviceD.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;

    @Column(name = "updated_at")
    private String updatedAt;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items;
}

