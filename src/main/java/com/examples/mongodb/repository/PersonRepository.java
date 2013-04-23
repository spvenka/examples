package com.examples.mongodb.repository;

import com.examples.mongodb.domain.Person;

public interface PersonRepository {
	public void logAllPersons();
	public int getAvarageAgeOfPerson();
	public void insertPerson(Person p);
	public void createPersonCollection();
	public void dropPersonCollection();
}
