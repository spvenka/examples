package com.examples.mongodb.repository;

import java.util.List;

import com.examples.mongodb.domain.Person;

public interface PersonRepository {
	public List<Person> findAllPersons();
	public void insertPerson(Person p);
	public void createPersonCollection();
	public void dropPersonCollection();
}
