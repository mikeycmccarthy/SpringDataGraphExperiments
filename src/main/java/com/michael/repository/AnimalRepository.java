package com.michael.repository;

import com.michael.model.Animal;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AnimalRepository extends GraphRepository<Animal> {
}
