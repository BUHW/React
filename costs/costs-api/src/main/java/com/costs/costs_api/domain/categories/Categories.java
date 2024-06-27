package com.costs.costs_api.domain.categories;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "categories")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Categories {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;
}
