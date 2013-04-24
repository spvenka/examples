package com.examples.mongodb.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.examples.mongodb.domain.Person;
import com.examples.mongodb.repository.PersonRepository;

/**
 * Repository for {@link Person}s
 */
@Repository
@Scope("singleton")
public class PersonRepositoryImpl implements PersonRepository{

    static final Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    @Autowired
    MongoTemplate mongoTemplate;
    
    @Override
    public List<Person> findAllPersons() {
        return mongoTemplate.findAll(Person.class);
    }

    @Override
    public void insertPerson(Person p) {

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
