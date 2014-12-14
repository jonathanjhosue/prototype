package com.company.prototype.rest;

import java.io.StringReader;
import java.util.Calendar;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.company.prototype.model.bean.Client;
import com.company.prototype.model.bean.StateAccount;
import com.company.prototype.service.ClientService;
import com.company.prototype.service.AccountService;

@Path("/clientWs")
public class ClientWs {
	
	@Inject
	ClientService service;
	
	/*
	 * Los parametros a los metodos pueden ser
	 * @PathParam /myapp/xml/id
	 * @QueryParam /myapp/xml?id=1
	 * @MatrixParam /myapp/xml;id=1
	 * @HeaderParam, @CookieParam, @FormParam  
	 * */
	
	
	@GET
	@Path("/text")
	//@Produces(MediaType.TEXT_PLAIN)
	public String getTest(@DefaultValue("1") @QueryParam("id") int id){
		
		return "Connection Works "+id;
		
	}
	
	@POST
	@Path("/search/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Client getClientJSON(@FormParam("entity") String entity, @FormParam("client") String client){
		
		return service.getClient(entity, client);
		
	}
	
	
	@POST
	@Path("/search/xml")
	@Produces(MediaType.APPLICATION_XML)
	public Client getClientXML(@FormParam("entity")String entity, @FormParam("client")String client){
		
		return service.getClient(entity, client);
		
	}
	
	
	// Intercepts an HTTP POST to update an existing Person. Data is coming in JSON format
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveContact(String clientInfo) {
     
         JsonReader reader = Json.createReader(new StringReader(clientInfo));
         JsonObject obj = reader.readObject();
         String name = obj.getString("name");
         String surname = obj.getString("surname");
         String address = obj.getString("address");
         
        /* Query query = em.createQuery("FROM Person where name = :name and surname = :surname");
         query.setParameter("name", name);
         query.setParameter("surname", surname);
         Person person = (Person) query.getSingleResult();
         person.setAddress(address);
         em.persist(person);*/
    }


	@POST
	@Produces("text/html")
	public Response createProperty(	@FormParam("key")String key,
									@FormParam("value")String value) {
		//int n = ejb.addToList(key,value);
	return Response.ok("Success").build();
	}
	
	
	// Intercepts an HTTP POST. Deletes a Person Entity. Data is coming in JSON format
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public void delete(String message) {
         
       System.out.println("Delete action, Not implemented ");
    }
    
    
    @POST
	@Path("/add")
	public Response addUser(
		@FormParam("name") String name,
		@FormParam("age") int age) {
 
		return Response.status(200)
			.entity("addUser is called, name : " + name + ", age : " + age)
			.build();
 
	}
	

}
