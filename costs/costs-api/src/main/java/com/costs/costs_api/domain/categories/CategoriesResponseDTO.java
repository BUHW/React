package com.costs.costs_api.domain.categories;

import java.util.UUID;

// DTOs são apenas objetos de transferência de dados, ou seja, não devem ficar na camada de domínio
// Não utilizado. Deveria ser removido
public record CategoriesResponseDTO(UUID id, String name) {
}
