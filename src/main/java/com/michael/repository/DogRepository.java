package com.michael.repository;

import com.michael.model.Dog;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface DogRepository extends GraphRepository<Dog> {
    Dog findByBreed(String dogBreed);
}
