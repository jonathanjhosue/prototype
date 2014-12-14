package com.company.prototype.rest;

import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.company.prototype.model.bean.AuthorizationResult;
import com.company.prototype.model.bean.Transaction;
import com.company.prototype.soap.AuthorizerWs;
import com.company.prototype.util.ApplicationConfiguration.AuthorizerResponse;


/*
 * Esta clase ejemplifica la interconección entre los servicios rest y SOAP
 * Para lo cual esta clase es un servicio Rest que a su vez se conecta como cliente 
 * a un servicio SOAP para obtener los datos 
 * 
 * */
@Path("/authorizerWsRest")
public class AuthorizerWsRest {
	
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
	
	
	/*
	 * Automaticamente hace el marshall desde el JSON a la transaction
	 * */
	@POST
	@Path("/authorize/json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AuthorizationResult authorizeJSON(Transaction transaction){
		AuthorizationResult r= new AuthorizationResult(AuthorizerResponse.DESCONOCIDO);
		/*
		 * ejemplo de connección a un webservice, en este caso se usa una estructura de clases interna, 
		 * pero en un contexto de cliente está estructura de clases debería ser generada tal como lo hace el WebServiceConsumer
		 * */
		
		URL wsdlDocumentLocation = null;
		String namespaceURI = "http://soap.prototype.company.com/";
		String servicePort = "AuthorizerWebService";
		String portName = "AuthorizerWebServicePort";
		QName serviceQN = new QName(namespaceURI, servicePort);
		QName portQN = new QName(namespaceURI, portName);
		 
		// Creates a service instance
		Service service;
		AuthorizerWs webService;
		
		try {
			wsdlDocumentLocation =new URL("http://localhost:8080/prototype-web/AuthorizerWebService?wsdl");
			
			service= Service.create(wsdlDocumentLocation, serviceQN);
			
			webService= service.getPort(portQN, AuthorizerWs.class);
			
			
			r= webService.authorize(transaction);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			r= new AuthorizationResult(AuthorizerResponse.ERROR,e.getMessage());
			
		}catch (Exception e){
			e.printStackTrace();	
			r= new AuthorizationResult(AuthorizerResponse.ERROR,e.getMessage());
		}
		
		return r;
		
	}
	
	
	@POST
	@Path("/authorize/xml")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public AuthorizationResult authorizeXML(Transaction transaction){
		
		return authorizeJSON(transaction);
	}
	
	
	/*
	@POST
	@Path("/authorize2/xml")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public AuthorizationResult authorizeXML(MultivaluedMap<String,String> multivaluedMap){
		
		return authorizeJSON(new Transaction());
	}
	*/
		
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
