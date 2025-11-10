package com.example.serviceC.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.serviceC.repositories.CategoryRepository;
import com.example.serviceC.entities.Category;
import com.example.serviceC.entities.Item;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository repo;

    public CategoryController(CategoryRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Category> getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Category getOne(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Category create(@RequestBody Category c) {
        return repo.save(c);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category c) {
        c.setCode(c.getCode());
        c.setName(c.getName());
        return repo.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @GetMapping("/{id}/items")
    public List<Item> getCategoryItems(@PathVariable Long id) {
        return repo.findById(id).get().getItems();
    }
}
