package com.examples.mongodb.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    public List<Person> findByName(String name) {
        return mongoTemplate.find(new Query(Criteria.where("name").is(name)), Person.class);
    }

    @Override
    public Person findById(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), Person.class);
    }      
    
    @Override
    public void create(Person person) {
        mongoTemplate.insert(person);
    }
    
    @Override
    public void update(Person person)	{
		mongoTemplate.save(person);
	} 

    @Override
    public void delete(Person person) {
    	mongoTemplate.remove(person);
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
