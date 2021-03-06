package com.examples.mongodb.service.impl;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.examples.mongodb.domain.Person;
import com.examples.mongodb.repository.PersonRepository;
import com.examples.mongodb.service.PersonService;

/**
 * Service for {@link Person}s
 */
@Service
@Scope("singleton")
public class PersonServiceImpl implements PersonService{

    static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired private PersonRepository personRepository;
    
    @Override
    public List<Person> findAllPersons() {
    	List<Person> results = personRepository.findAllPersons();

        logger.info("Total amount of persons: {}", results.size());
        logger.info("Results: {}", results);
        
        return results;
    }
    
    @Override
    public List<Person> findByName(String name) {
    	List<Person> results = personRepository.findByName(name);
        return results;
    }
    
    @Override
    public Person findById(String id) {
    	return personRepository.findById(id);
    }  
    
    @Override
    public void create(Person person) {
        personRepository.create(person);
    }

    @Override
    public void update(Person person) {
        personRepository.update(person);
    }

    @Override
    public void delete(Person person) {
    	personRepository.delete(person);
    }

    @Override
    public int getAvarageAgeOfPerson() {
    	List<Person> results = personRepository.findAllPersons();
        int age = 0;
        Iterator<Person> personIterator = results.iterator();
        while (personIterator.hasNext()) {
            Person nextPerson = personIterator.next();
            age += nextPerson.getAge();
        }
        return age / results.size();
    } 

    @Override
    public void dropPersonCollection() {
        personRepository.dropPersonCollection();
    }

    @Override
    public void createPersonCollection() {
        personRepository.createPersonCollection();
    }
}
