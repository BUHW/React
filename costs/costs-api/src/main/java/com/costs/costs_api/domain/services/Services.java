package com.costs.costs_api.domain.services;

import com.costs.costs_api.domain.project.Project;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;

@Table(name = "services")
@Entity
@Setter
@Getter
@NoArgsConstructor
// Pelo que eu vi, vão não está utilizando o construtor com todos os argumentos. Poderia remover
@AllArgsConstructor
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private Double cost;

    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;

}
