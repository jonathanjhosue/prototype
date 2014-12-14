package com.company.prototype.rest;

import java.util.Calendar;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.company.prototype.model.bean.Account;
import com.company.prototype.model.bean.StateAccount;
import com.company.prototype.service.AccountService;

@Path("/accountWs")
public class AccountWs {
	
	@Inject
	AccountService service;
	
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
	
	
	
	@GET
	@Path("/{entityId}/{accountNumber}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Account getStateAccountJSON(@PathParam("entityId")String entityId, @PathParam("accountNumber")String accountNumber){
		
		return service.getAccount(entityId, accountNumber);
		
	}
	
	
	
	
	@GET
	@Path("/{entityId}/{accountNumber}/xml")
	@Produces(MediaType.APPLICATION_XML)
	public Account getStateAccountXML(@PathParam("entityId")String entityId, @PathParam("accountNumber")String accountNumber){
		
		return service.getAccount(entityId, accountNumber);
		
	}
	
	
/*
	@POST
	@Produces("text/html")
	public Response createProperty(	@FormParam("key")String key,
									@FormParam("value")String value) {
		//int n = ejb.addToList(key,value);
	return Response.ok("Success").build();
	}
*/
	
	
	

}
