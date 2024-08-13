package com.costs.costs_api.domain.services;

import com.costs.costs_api.domain.project.Project;

// DTOs são apenas objetos de transferência de dados, ou seja, não devem ficar na camada de domínio
// Não utilizado. Poderia ser removido
public record ServicesRequestDTO(String name, Double cost, String Description, Project project_id) {
}
