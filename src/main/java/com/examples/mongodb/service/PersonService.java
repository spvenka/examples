package com.examples.mongodb.service;

import java.util.List;

import com.examples.mongodb.domain.Person;

public interface PersonService {
	public List<Person> findAllPersons();
	public List<Person> findByName(String query);
	public Person findById(String id);
	public void create(Person person);
	public void update(Person person);
	public void delete(Person person);
	public int getAvarageAgeOfPerson();
	public void createPersonCollection();
	public void dropPersonCollection();
}
