package com.examples.mongodb.service;

import java.util.List;

import com.examples.mongodb.domain.Person;

public interface PersonService {
	public List<Person> findAllPersons();
	public int getAvarageAgeOfPerson();
	public void insertPerson(Person p);
	public void createPersonCollection();
	public void dropPersonCollection();
}
