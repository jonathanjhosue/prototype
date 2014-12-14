package com.company.prototype.service;

import com.company.prototype.model.bean.AuthorizationResult;
import com.company.prototype.model.bean.Transaction;
import com.company.prototype.model.entity.EstadoCuenta;
import com.company.prototype.model.entity.Transaccion;


public interface Authorizer {
	
	public AuthorizationResult validation(Transaccion t);
	
	public AuthorizationResult process(Transaccion t);
	
	//public AuthorizationResult validationData(Transaccion t);
	
	/*public AuthorizationResult checkTransactions(Transaccion t);
	
	public AuthorizationResult checkMovements(Transaccion t);
	
	public AuthorizationResult updateTransaction(Transaccion t);
	
	public AuthorizationResult update(EstadoCuenta c, Transaccion t);
	
	public AuthorizationResult generateMovements(Transaccion t);*/
}
