package com.michael.repository;

import com.michael.model.Person;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 */
public interface PersonRepository extends GraphRepository<Person> {
    Person findByPersonId(Long personId);

}
