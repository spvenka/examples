package com.examples.mongodb.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import com.examples.mongodb.domain.Person;

/*Required in case its unable to marshal JSON/xml response. Making Person class @XmlRootElement annotated resolves the issue
  @Produces({ "application/json", "application/xml"})
  @Provider
 */
public class XMLWriter implements MessageBodyWriter<ArrayList<Person>>{

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public long getSize(ArrayList<Person> t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return t.size();
	}

	@Override
	public void writeTo(ArrayList<Person> t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		// TODO Auto-generated method stub
		t.size();
	}

}
