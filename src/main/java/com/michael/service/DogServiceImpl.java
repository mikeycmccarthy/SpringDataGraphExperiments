package com.michael.service;

import com.michael.model.Dog;
import com.michael.repository.DogRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DogServiceImpl implements DogService {

    @Resource
    private DogRepository dogRepository;

    public Dog save(Dog dog) {
        return dogRepository.save(dog);
    }

    public Dog findDog(String dogBreed) {
        return dogRepository.findByBreed(dogBreed);
    }
}
