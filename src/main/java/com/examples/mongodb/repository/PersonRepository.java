package com.examples.mongodb.repository;

import java.util.List;

import com.examples.mongodb.domain.Person;

public interface PersonRepository {
	public List<Person> findAllPersons();
	public List<Person> findByName(String name);
	public Person findById(String id);
	public void create(Person person);
	public void update(Person person);
	public void delete(Person person);
	public void createPersonCollection();
	public void dropPersonCollection();
}
