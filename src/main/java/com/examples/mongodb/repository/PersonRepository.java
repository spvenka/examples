package com.examples.mongodb.repository;

public interface PersonRepository {
	public void logAllPersons();
	public int getAvarageAgeOfPerson();
	public void insertPersonWithNameJohnAndRandomAge();
	public void createPersonCollection();
	public void dropPersonCollection();
}
