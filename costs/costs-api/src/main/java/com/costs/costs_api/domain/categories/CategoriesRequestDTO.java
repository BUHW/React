package com.costs.costs_api.domain.categories;

// DTOs são apenas objetos de transferência de dados, ou seja, não devem ficar na camada de domínio
// Não utilizado. Deveria ser removido
public record CategoriesRequestDTO(String name) {
}
