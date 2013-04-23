package com.examples.mongodb.repository.impl;

import java.util.Iterator;
import java.util.List;

import com.examples.mongodb.domain.Person;
import com.examples.mongodb.repository.PersonRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Person}s
 */
@Repository
public class PersonRepositoryImpl implements PersonRepository{

    static final Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    @Autowired
    MongoTemplate mongoTemplate;
    
    @Override
    public void logAllPersons() {
        List<Person> results = mongoTemplate.findAll(Person.class);
        logger.info("Total amount of persons: {}", results.size());
        logger.info("Results: {}", results);
    }

    /**
     * Calculates the average age of a {@link Person}.
     *
     * @return the average age
     */
    @Override
    public int getAvarageAgeOfPerson() {
        List<Person> results = mongoTemplate.findAll(Person.class);
        int age = 0;
        Iterator<Person> personIterator = results.iterator();
        while (personIterator.hasNext()) {
            Person nextPerson = personIterator.next();
            age += nextPerson.getAge();
        }
        return age / results.size();
    }

    @Override
    public void insertPerson(Person p) {
        //get random age between 1 and 100
        //double age = Math.ceil(Math.random() * 100);

        //Person p = new Person("John", (int) age);

        mongoTemplate.insert(p);
    }

    /**
     * Create a {@link Person} collection if the collection does not already exists
     */
    @Override
    public void createPersonCollection() {
        if (!mongoTemplate.collectionExists(Person.class)) {
            mongoTemplate.createCollection(Person.class);
        }
    }

    /**
     * Drops the {@link Person} collection if the collection does already exists
     */
    @Override
    public void dropPersonCollection() {
        if (mongoTemplate.collectionExists(Person.class)) {
            mongoTemplate.dropCollection(Person.class);
        }
    }
}
