package com.example.serviceC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.serviceC.entities.Item;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategoryId(Long categoryId);
}