package com.costs.costs_api.service.interfaces;

import com.costs.costs_api.domain.categories.Categories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriesServiceInterface {

    List<Categories> getAll();
    Optional<Categories> getById(UUID id);
    Categories create(Categories categories);
    Categories update(UUID id, Categories categories);
    void delete(UUID id);

}
