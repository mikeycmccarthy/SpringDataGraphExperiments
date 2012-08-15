package com.michael.service;

import com.michael.model.Person;

/**
 *
 */
public interface PersonService {
    Person save(Person person);
    
    Person find(Long personId);
}
