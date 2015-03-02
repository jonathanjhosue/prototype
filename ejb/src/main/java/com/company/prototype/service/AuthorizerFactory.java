package com.company.prototype.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.company.prototype.util.ApplicationConfiguration.TiposTransaccion;

@Stateless
public class AuthorizerFactory {
	
	
	@Inject
	private AuthorizerCompras authorizerCompras;
	
	@Inject
	private AuthorizerDeposito authorizerDeposito;
	
	@Inject
	private AuthorizerRetiro authorizerRetiro;
	
	@Inject
	private AuthorizerTransferenciaInterna authorizerTransferenciaInterna;
	
	
	public Authorizer getAuthorizer(TiposTransaccion type){
		

		if (TiposTransaccion.COMPRA.equals(type)){
			return authorizerCompras;
		
		}else if (TiposTransaccion.DEPOSITO.equals(type)){
			return authorizerDeposito;
			
		}else if (TiposTransaccion.RETIRO.equals(type)){
			return authorizerRetiro;
			
		}
		else if (TiposTransaccion.TRANSFERENCIA_INTERNA.equals(type)){
			return authorizerTransferenciaInterna;
			
		}else if (TiposTransaccion.TRANSFERENCIA_EXTERNA.equals(type)){
			
			
		}else if (TiposTransaccion.SIMPE.equals(type)){
			
			
		}else if (TiposTransaccion.REEMBOLSO.equals(type)){
			
			
		}else if (TiposTransaccion.ERROR.equals(type)){
			
			
		}else if (TiposTransaccion.DESCONOCIDO.getValue().equals(type)){
			
			
		}
		
		return authorizerCompras;
		
	}
	

}
