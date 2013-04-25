package com.examples.mongodb.resource.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
    //@Produces({ "application/json"})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Person> findAllPersons() {
    	List<Person> results = personService.findAllPersons();
        /*Required in case its unable to marshal JSON/xml response. Making Person class @XmlRootElement annotated resolves the issue
          return Response.ok(results).build();
    	  return Response.created(uriInfo.getAbsolutePath()).entity((Person)results.get(0)).build();*/
    	return results;
    }

    @Override
    @GET @Path("search/{query}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Person> findByName(@PathParam("query") String query) {
		logger.info("Finding person by name: " + query);
		return personService.findByName(query);
	}

	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Person findById(@PathParam("id") String id) {
		logger.info("Finding person by id: " + id);
		return personService.findById(id);
	}

    @Override
    @POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Person create(Person person) {
		logger.info("Creating person: " + person.getName());
		personService.create(person);
		return person;
	}

    @Override
    @PUT @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Person update(Person person) {
		logger.info("Updating person: " + person.getName());
		personService.update(person);
		return person;
	}
	
    @Override
    @DELETE @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	//public void remove(@PathParam("name") String name) {
	public Person delete(Person person) {
		logger.info("Deleting person: " + person.getName());
		personService.delete(person);
		return person;
	}
}
