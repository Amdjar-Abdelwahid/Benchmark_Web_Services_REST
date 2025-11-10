package com.example.serviceD.repositories;

import com.example.serviceD.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import java.util.List;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {

    @RestResource(path = "byCategory")
    List<Item> findByCategoryId(@Param("categoryId") Long categoryId);
}
