package com.costs.costs_api.repositories;

import com.costs.costs_api.domain.services.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicesRepository extends JpaRepository<Services, UUID> {
}
