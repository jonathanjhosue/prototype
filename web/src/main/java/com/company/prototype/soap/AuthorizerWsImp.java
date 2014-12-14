package com.company.prototype.soap;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.company.prototype.model.bean.AuthorizationResult;
import com.company.prototype.model.bean.Transaction;
import com.company.prototype.model.entity.Transaccion;
import com.company.prototype.service.AuthorizerService;


/*
 * También se puede usar un deployment descriptor webservice.xml para definirlo
 * 
 * */
@WebService(portName = "AuthorizerWebServicePort", serviceName = "AuthorizerWebService")
public class AuthorizerWsImp implements AuthorizerWs{
	
	@Inject
	AuthorizerService service;
	
	@WebResult(name = "AuthorizationResult")
	public AuthorizationResult authorize(Transaction t){
			
		return service.process(t);
		
	}
	
	
	
	/*
	 * methodo excluido para el webservice
	 * */
	@WebMethod(exclude = true)
	public void validate(Long creditCardNumber) {
	// Business logic
	
	}



	@Override
	public String simple(Transaction t) {
		// TODO Auto-generated method stub
		return t.getCardNumber()+" Test";
	}

	
	
	

}
