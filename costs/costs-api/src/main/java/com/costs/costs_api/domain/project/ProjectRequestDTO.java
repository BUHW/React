package com.costs.costs_api.domain.project;

import com.costs.costs_api.domain.categories.Categories;
import com.costs.costs_api.domain.services.Services;

import java.util.List;
// Sempre que possível, remover imports não utilizados. Poderia ter usado o atalho Ctrl+Alt+O do IntelliJ
import java.util.UUID;

// DTOs são apenas objetos de transferência de dados, ou seja, não devem ficar na camada de domínio
public record ProjectRequestDTO(String name, Double budget, Double cost, Categories category, List<Services> services) {
}
