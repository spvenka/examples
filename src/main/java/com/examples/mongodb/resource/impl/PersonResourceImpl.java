package com.examples.mongodb.resource.impl;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.examples.mongodb.domain.Person;
import com.examples.mongodb.resource.PersonResource;
import com.examples.mongodb.service.PersonService;

/**
 * Service for {@link Person}s
 */
@Component
@Path("/persons")
//@Scope("request")
public class PersonResourceImpl implements PersonResource{

    static final Logger logger = LoggerFactory.getLogger(PersonResourceImpl.class);

    @Autowired private PersonService personService;
    
    @Context
	private UriInfo uriInfo;
    
    @Override
    @GET
    @Produces({ "application/json"})
    public List<Person> findAllPersons() {
    	List<Person> results = personService.findAllPersons();
        /*Required in case its unable to marshal JSON/xml response. Making Person class @XmlRootElement annotated resolves the issue
          return Response.ok(results).build();
    	  return Response.created(uriInfo.getAbsolutePath()).entity((Person)results.get(0)).build();*/
    	return results;
    }

    @Override
    public int getAvarageAgeOfPerson() {
        return personService.getAvarageAgeOfPerson();
    }

    @Override
    public void insertPerson(Person p) {
    	personService.insertPerson(p);
    }

    @Override
    public void createPersonCollection() {
    	personService.createPersonCollection();
    }

    @Override
    public void dropPersonCollection() {
    	personService.dropPersonCollection();
    }
}
