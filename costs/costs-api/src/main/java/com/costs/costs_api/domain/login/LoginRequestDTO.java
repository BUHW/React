package com.costs.costs_api.domain.login;

// DTOs são apenas objetos de transferência de dados, ou seja, não devem ficar na camada de domínio
public record LoginRequestDTO(String login, String password) {}
