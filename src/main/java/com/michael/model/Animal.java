package com.michael.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Animal {

    public Long getId() {
        return id;
    }

    @GraphId
    private Long id;

    @Indexed(indexName = "mongrels")
    private String breed;

    @Indexed
    private Long numberOfLegs;

    public Long getNumberOfLegs() {
        return numberOfLegs;
    }

    public Animal() {
    }

    public Animal(String breed, Long numberOfLegs) {
        this.breed = breed;
        this.numberOfLegs = numberOfLegs;
    }

    public String getBreed() {
        return breed;
    }

}
