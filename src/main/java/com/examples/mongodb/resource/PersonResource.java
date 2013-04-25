package com.examples.mongodb.resource;

import java.util.List;

import com.examples.mongodb.domain.Person;

public interface PersonResource {
	public List<Person> findAllPersons();
	public List<Person> findByName(String query);
	public Person create(Person person);
	public Person update(Person person);
	public Person delete(Person person);
}
