package com.costs.costs_api.service;

import com.costs.costs_api.domain.categories.Categories;
import com.costs.costs_api.repositories.CategoriesRepository;
import com.costs.costs_api.service.interfaces.CategoriesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriesService implements CategoriesServiceInterface {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<Categories> getAll() {
        return categoriesRepository.findAll();
    }

    @Override
    // O Optional deveria estar sendo tratado dentro do Service, e não no Controller,
    // ou seja, o método deveria retornar um Categories
    public Optional<Categories> getById(UUID id) {
        return categoriesRepository.findById(id);
    }

    @Override
    public Categories create(Categories categories) {
        // Não acho esse tratamento de exceção necessário, pois o Spring já trata exceções de banco de dados
        try {
            return categoriesRepository.save(categories);
        } catch (Exception e) {
            // Não é uma boa prática lançar exceções genéricas. Idealmente, deveria ser lançada uma exceção mais específica
            // como ErrorSavingCategoryException, que estenderia de RuntimeException
            throw new RuntimeException("Erro ao realizar inserção de Categoria, motivo" + e.getMessage());
        }
    }

    @Override
    public Categories update(UUID id, Categories categories) {
        Categories categoriesUpdate = categoriesRepository.findById(id)
                // O ideal seria lançar uma exceção mais específica, como CategoryNotFoundException,
                // que estenderia de RuntimeException
                .orElseThrow(() -> new RuntimeException("Project not found"));

        categoriesUpdate.setName(categories.getName());

        return categoriesRepository.save(categoriesUpdate);
    }

    @Override
    public void delete(UUID id) {
        categoriesRepository.deleteById(id);
    }

}
