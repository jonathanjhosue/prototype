package com.company.prototype.soap;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import org.junit.Test;

import com.company.prototype.model.bean.AuthorizationResult;
import com.company.prototype.model.bean.Transaction;
import com.company.prototype.util.ApplicationConfiguration.AuthorizerResponse;


//Ws Testing
public class AuthorizerWsTest {
	
	// Publishes the SOAP Web Service
	/*
	Endpoint endpoint = Endpoint.publish("http://localhost:8080/prototype-web/AuthorizerWebService", new AuthorizerWsImp());
	assertTrue(endpoint.isPublished());
	assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http",endpoint.getBinding().getBindingID());
	*/
	 
	// Needed properties to access the web service
	URL wsdlDocumentLocation = null;
	String namespaceURI = "http://soap.prototype.company.com/";
	String servicePort = "AuthorizerWebService";
	String portName = "AuthorizerWebServicePort";
	QName serviceQN = new QName(namespaceURI, servicePort);
	QName portQN = new QName(namespaceURI, portName);
	 
	// Creates a service instance
	Service service;
	AuthorizerWs webService;
		
	public AuthorizerWsTest() throws MalformedURLException{
		wsdlDocumentLocation =new URL("http://localhost:8080/prototype-web/AuthorizerWebService?wsdl");
		
		service= Service.create(wsdlDocumentLocation, serviceQN);
		
		webService= service.getPort(portQN, AuthorizerWs.class);
	}
	
	
	//comment the annotation @Test if you don't want to test it
	
	//@Test
	public void shouldCheckAuthorizerValidity() {
		

	}

	//@Test
	public void shouldCheckAuthorizerCompra() {
		AuthorizationResult r=null;	
		 
		// Invokes the web service
		Transaction t = new Transaction();
		t.setIdentification("INVOICE123");
		t.setTransactionType("01");
		t.setFromEntity("Entidad1");
		t.setDate(Calendar.getInstance().getTime());
		t.setCardNumber("4000000000000000000");
		t.setCurrencyCode("CRC");
		t.setValue(new BigDecimal(5200l));
		t.setMerchandId("COM00001");
		r= webService.authorize(t);
		assertTrue("Credit card should be valid", !AuthorizerResponse.ERROR.equals(r.getAuthorizerResponse()));
	}
	
	//@Test
	public void shouldCheckAuthorizerDeposito() {
		AuthorizationResult r=null;	
		// Invokes the web service
		Transaction t1 = new Transaction();
		t1.setIdentification("INVOICE123");
		t1.setTransactionType("02");//depósito	
		t1.setDate(Calendar.getInstance().getTime());
		t1.setCurrencyCode("CRC");	
		t1.setValue(new BigDecimal(5300l));
		t1.setToAccount("111111111111111002");
		t1.setToEntity("Entidad1");
		r= webService.authorize(t1);
		assertTrue("Autorization successfull", AuthorizerResponse.SUCCESSFUL.equals(r.getAuthorizerResponse()));
	}
	
	@Test
	public void shouldCheckAuthorizerTransferenciaInterna() {
		AuthorizationResult r=null;	
		// Invokes the web service
		Transaction t1 = new Transaction();
		t1.setTransactionType("04");//tranferencia	
		t1.setDate(Calendar.getInstance().getTime());
		t1.setCurrencyCode("CRC");	
		t1.setValue(new BigDecimal(5400l));
		t1.setFromAccount("111111111111111002");
		t1.setFromEntity("Entidad1");
		t1.setToAccount("111111111111111003");
		t1.setToEntity("Entidad1");
		r= webService.authorize(t1);
		assertTrue("Autorization successfull", AuthorizerResponse.SUCCESSFUL.equals(r.getAuthorizerResponse()));
		
	}
	

}
