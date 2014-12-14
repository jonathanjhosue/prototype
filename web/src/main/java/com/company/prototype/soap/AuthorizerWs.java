package com.company.prototype.soap;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.company.prototype.model.bean.AuthorizationResult;
import com.company.prototype.model.bean.Transaction;

@WebService
public interface AuthorizerWs {

	@WebResult(name = "AuthorizationResult")
	public AuthorizationResult authorize(Transaction t);
	

	public String simple(Transaction text);
}
