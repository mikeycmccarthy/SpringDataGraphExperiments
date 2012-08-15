package com.michael.service;

import com.michael.model.Person;
import com.michael.repository.PersonRepository;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    
    @Resource
    private PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person find(Long personId) {
        return personRepository.findByPropertyValue("personId", personId);
        //return personRepository.findByPersonId(personId);
    }

}
