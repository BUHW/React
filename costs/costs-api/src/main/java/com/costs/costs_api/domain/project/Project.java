package com.costs.costs_api.domain.project;

import com.costs.costs_api.domain.services.Services;
import com.costs.costs_api.domain.categories.Categories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;
import java.util.UUID;

@Table(name = "Project")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double budget;

    @Column(nullable = false)
    private Double cost = 0.0;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;

    @OneToMany
    @JoinColumn(name = "service_id")
    private List<Services> services;
}
