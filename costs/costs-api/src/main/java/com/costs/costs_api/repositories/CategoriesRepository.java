package com.costs.costs_api.repositories;

import com.costs.costs_api.domain.categories.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriesRepository extends JpaRepository<Categories, UUID> {
    Categories findCategoriesByName(String name);
}
