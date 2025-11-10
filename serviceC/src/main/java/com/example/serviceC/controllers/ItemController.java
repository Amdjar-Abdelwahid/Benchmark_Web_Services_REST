package com.example.serviceC.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.serviceC.repositories.ItemRepository;
import com.example.serviceC.entities.Item;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemRepository repo;

    public ItemController(ItemRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Item> getAll(@RequestParam(required = false) Long categoryId) {
        return categoryId != null ? repo.findByCategoryId(categoryId) : repo.findAll();
    }

    @GetMapping("/{id}")
    public Item getOne(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Item create(@RequestBody Item i) {
        return repo.save(i);
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable Long id, @RequestBody Item i) {
        i.setId(id);
        return repo.save(i);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
