package com.examples.mongodb.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.examples.mongodb.domain.Person;
import com.examples.mongodb.service.PersonService;
import com.examples.mongodb.service.impl.PersonServiceImpl;

/**
 * Small MongoDB application that uses spring data to interact with MongoDB.
 */
public class MongoDBApp {

    static final Logger logger = LoggerFactory.getLogger(MongoDBApp.class);

    public static void main( String[] args ) {
		logger.info("Bootstrapping MongoDemo application");

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		PersonService personService = (PersonService)context.getBean(PersonServiceImpl.class);

        // cleanup person collection before insertion
		personService.dropPersonCollection();

        //create person collection
		personService.createPersonCollection();

        //for(int i=0; i<20; i++) {
		personService.create(new Person("Mike", 35));
		personService.update(new Person("Tom", 30, "New Jersey"));
        //}
		
		List<Person> results = personService.findAllPersons();
		for(Person person:results){
			List<Person> results1 = personService.findByName(person.getName());
			for(Person person1:results1){
				Person person2 = personService.findById(person1.getPersonId());
				person2.getName();
			}
		}
        logger.info("Avarage age of a person is: {}", personService.getAvarageAgeOfPerson());

        logger.info("Finished MongoDemo application");
	}
}
