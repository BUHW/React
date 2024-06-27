package com.costs.costs_api.controller;

import com.costs.costs_api.domain.categories.Categories;
import com.costs.costs_api.service.CategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public List<Categories> getAll() {
        return categoriesService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Categories> getById(@PathVariable UUID id) {
        return categoriesService.getById(id);
    }

    @PostMapping
    public Categories create(@RequestBody Categories categories) {
        return categoriesService.create(categories);
    }

    @PutMapping("/{id}")
    public Categories update(@PathVariable UUID id, @RequestBody Categories categories) {
        return categoriesService.update(id, categories);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        categoriesService.delete(id);
        return ResponseEntity.ok("Categoria excluida com sucesso!");
    }

}
