package com.michael.service;

import com.michael.model.Dog;

public interface DogService {


    Dog save(Dog dog);

    Dog findDog(String dogBreed);

}
