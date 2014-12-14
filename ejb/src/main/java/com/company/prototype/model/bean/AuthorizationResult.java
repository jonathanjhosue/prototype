package com.company.prototype.model.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.company.prototype.util.ApplicationConfiguration.AuthorizerResponse;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorizationResult {
	private Transaction transaction;
	
	private String message; 
	private AuthorizerResponse authorizerResponse=AuthorizerResponse.DESCONOCIDO;
	
	public Transaction getTransaction() {
		return transaction;
	}


	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}


	public void setAuthorizerResponse(AuthorizerResponse authorizerResponse) {
		this.authorizerResponse = authorizerResponse;
	}


	public AuthorizerResponse getAuthorizerResponse() {
		return authorizerResponse;
	}


	public void setResponseCode(AuthorizerResponse responseCode) {
		this.authorizerResponse = responseCode;
	}


	public Transaction getTransaccion() {
		return transaction;
	}


	public void setTransaccion(Transaction transaccion) {
		this.transaction = transaccion;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	public AuthorizationResult(){

	}
	
	public AuthorizationResult(AuthorizerResponse responseCode){
		this(responseCode,"");
	}	
	
	public AuthorizationResult(AuthorizerResponse responseCode, String message){
		this.authorizerResponse=responseCode;
		this.message=message;
	}



	

}
