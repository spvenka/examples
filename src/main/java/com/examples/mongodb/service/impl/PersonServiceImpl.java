package com.examples.mongodb.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.mongodb.domain.Person;
import com.examples.mongodb.repository.PersonRepository;
import com.examples.mongodb.service.PersonService;

/**
 * Service for {@link Person}s
 */
@Service
public class PersonServiceImpl implements PersonService{

    static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonRepository personRepository;
    
    @Override
    public void logAllPersons() {
        personRepository.logAllPersons();
    }

    @Override
    public int getAvarageAgeOfPerson() {
        return personRepository.getAvarageAgeOfPerson();
    }

    @Override
    public void insertPerson(Person p) {
        personRepository.insertPerson(p);
    }

    @Override
    public void createPersonCollection() {
        personRepository.createPersonCollection();
    }

    @Override
    public void dropPersonCollection() {
        personRepository.dropPersonCollection();
    }
}
