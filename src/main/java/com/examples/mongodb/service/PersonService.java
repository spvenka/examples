package com.examples.mongodb.service;

import com.examples.mongodb.domain.Person;

public interface PersonService {
	public void logAllPersons();
	public int getAvarageAgeOfPerson();
	public void insertPerson(Person p);
	public void createPersonCollection();
	public void dropPersonCollection();
}
