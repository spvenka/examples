package com.examples.mongodb.resource;

import java.util.List;

import javax.ws.rs.core.Response;

import com.examples.mongodb.domain.Person;

public interface PersonResource {
	public List<Person> findAllPersons();
	public int getAvarageAgeOfPerson();
	public void insertPerson(Person p);
	public void createPersonCollection();
	public void dropPersonCollection();
}
