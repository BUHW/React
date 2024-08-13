package com.costs.costs_api.domain.project;

import com.costs.costs_api.domain.categories.Categories;
import com.costs.costs_api.domain.services.Services;

import java.util.List;
import java.util.UUID;

// DTOs são apenas objetos de transferência de dados, ou seja, não devem ficar na camada de domínio
// Não utilizado. Deveria ser removido
public record ProjectResponseDTO(UUID id, String name, Double budget, Double cost, Categories category, List<Services> services) {
}
