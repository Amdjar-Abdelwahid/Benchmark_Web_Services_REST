package com.example.serviceC.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private String name;

    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items;

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public List<Item> getItems() { return items; }

    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setItems(List<Item> items) { this.items = items; }
}

