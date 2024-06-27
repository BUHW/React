package com.costs.costs_api.domain.project;

import java.util.UUID;

public record ProjectResponseDTO(UUID id, String name, Double budge, Double cost) {
}
