package com.michael.service;

import com.michael.model.Dog;
import com.michael.repository.AnimalRepository;
import com.michael.repository.DogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/graph-test-context.xml"})
public class DogServiceImplTest {

    @Resource
    private DogService dogService;

    @Resource
    private AnimalRepository animalRepository;

    @Resource
    private DogRepository dogRepository;
    private static final String DOG_BREED = "pug";

    @Test
    @Transactional
    public void dogSavedToAnimalRepositoryIsRetrievableByStringPropertyFromBothRepositories() throws Exception {

        animalRepository.save(new Dog(DOG_BREED, 3L));

        assertTrue(animalRepository.findAllByPropertyValue("breed", DOG_BREED).iterator().hasNext());
        assertTrue(dogRepository.findAllByPropertyValue("breed", DOG_BREED).iterator().hasNext());

    }

    @Test
    @Transactional
    public void dogSavedToDogRepositoryIsRetrievableByStringPropertyFromBothRepositories() throws Exception {

        dogRepository.save(new Dog(DOG_BREED, 3L));

        assertTrue(animalRepository.findAllByPropertyValue("breed", DOG_BREED).iterator().hasNext());
        assertTrue(dogRepository.findAllByPropertyValue("breed", DOG_BREED).iterator().hasNext());

    }

}
