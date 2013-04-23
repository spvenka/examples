package com.examples.mongodb.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.examples.mongodb.repository.impl.PersonRepositoryImpl;

/**
 * Small MongoDB application that uses spring data to interact with MongoDB.
 */
public class MongoDBApp {

    static final Logger logger = LoggerFactory.getLogger(MongoDBApp.class);

    public static void main( String[] args ) {
		logger.info("Bootstrapping MongoDemo application");

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");

        PersonRepositoryImpl personRepository = context.getBean(PersonRepositoryImpl.class);

        // cleanup person collection before insertion
        personRepository.dropPersonCollection();

        //create person collection
        personRepository.createPersonCollection();

        for(int i=0; i<20; i++) {
            personRepository.insertPersonWithNameJohnAndRandomAge();
        }

        personRepository.logAllPersons();
        logger.info("Avarage age of a person is: {}", personRepository.getAvarageAgeOfPerson());

        logger.info("Finished MongoDemo application");
	}
}
