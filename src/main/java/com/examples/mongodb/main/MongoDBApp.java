package com.examples.mongodb.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.examples.mongodb.domain.Person;
import com.examples.mongodb.service.PersonService;

/**
 * Small MongoDB application that uses spring data to interact with MongoDB.
 */
public class MongoDBApp {

    static final Logger logger = LoggerFactory.getLogger(MongoDBApp.class);

    public static void main( String[] args ) {
		logger.info("Bootstrapping MongoDemo application");

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");

		PersonService personService = (PersonService)context.getBean("personServiceImpl");

        // cleanup person collection before insertion
		personService.dropPersonCollection();

        //create person collection
		personService.createPersonCollection();

        //for(int i=0; i<20; i++) {
		personService.insertPerson(new Person("Tom", 30));
        //}

		personService.logAllPersons();
        logger.info("Avarage age of a person is: {}", personService.getAvarageAgeOfPerson());

        logger.info("Finished MongoDemo application");
	}
}
