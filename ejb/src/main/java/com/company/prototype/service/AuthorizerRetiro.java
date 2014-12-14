package com.company.prototype.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.company.prototype.model.bean.AuthorizationResult;
import com.company.prototype.model.entity.Cuenta;
import com.company.prototype.model.entity.EstadoCuenta;
import com.company.prototype.model.entity.Tarjeta;
import com.company.prototype.model.entity.Transaccion;
import com.company.prototype.util.TransactionUtils;
import com.company.prototype.util.Validator;
import com.company.prototype.util.ApplicationConfiguration.AuthorizerResponse;
import com.company.prototype.util.ApplicationConfiguration.EstadosEstadoCuenta;
import com.company.prototype.util.ApplicationConfiguration.EstadosTransaccion;
import com.company.prototype.util.ApplicationConfiguration.TiposTransaccion;

@Named("authorizerRetiro")
@SessionScoped
public class AuthorizerRetiro implements Authorizer,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final String transactionTypeCode= TiposTransaccion.RETIRO.getValue();

	final Validator v= new Validator();
	
	@EJB
	TarjetaFacade tarjetaFacade;
	
	@EJB
	CuentaFacade cuentaFacade;
	
	@EJB
	EstadoCuentaFacade estadoCuentaFacade;
	
	@EJB
	TransaccionFacade transaccionFacade;
	
	public AuthorizerRetiro(){
		
	}

	@Override
	public AuthorizationResult validation(Transaccion t) {
		
		AuthorizationResult r=new AuthorizationResult(AuthorizerResponse.SUCCESSFUL);
		
		if(!transactionTypeCode.equals(t.getTipotransaccion())){
			r.setResponseCode(AuthorizerResponse.INVALID_TYPE);
			r.setMessage("Validations.Transaction.InvalidTransactionType");
			
		}else if(v.isNullorEmpty(t.getId())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidTransactionId");
			
		}else if(v.isNullorEmpty(t.getCuenta())){ // Cuenta From
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.AccountMissing");
			
		}else if(v.isNullorEmpty(t.getMonedaId())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidCurrency");
			
		}else if(v.isNullorEmpty(t.getValor())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidValue");
			
		}else if(t.getValor().compareTo(BigDecimal.ZERO)<=0){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidValue");			
		}
		
		return r;
	}


	public AuthorizationResult validationData(Cuenta cuenta, EstadoCuenta ec,Transaccion t) {		
		AuthorizationResult r=new AuthorizationResult(AuthorizerResponse.DESCONOCIDO);
		
		if(v.isNullorEmpty(cuenta)){	//cuenta To		
			//consulta externa, no implementada			
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.AccountNotFound");
			
		}else{
			if(v.isNullorEmpty(ec)){	
				r.setResponseCode(AuthorizerResponse.INVALID_DATA);
				r.setMessage("Validations.Transaction.StateAccountNotFound");
			
			}else{				
				if(v.isValidStateToProcess(cuenta)){
					
					BigDecimal newSaldo=ec.getSaldoactual().subtract(t.getValor());
					if(newSaldo.compareTo(BigDecimal.ZERO)<0){					
						r.setResponseCode(AuthorizerResponse.REJECTED);
						r.setMessage("Validations.Transaction.InsufficientFunds");				
					}else{
						r.setResponseCode(AuthorizerResponse.SUCCESSFUL);					
					}					
				}
				else{
					r.setResponseCode(AuthorizerResponse.REJECTED);
					r.setMessage("Validations.Transaction.InvalidState");				
				}				
			}		
		}
		
		return r;
	}


	@Override
	public AuthorizationResult process(Transaccion t) {
		final AuthorizerResponse success=AuthorizerResponse.SUCCESSFUL;
		AuthorizationResult r=new AuthorizationResult(AuthorizerResponse.DESCONOCIDO);
		
		r=validation(t);
		if(success.equals(r.getAuthorizerResponse())){			
			try{					
				Cuenta cuentaFrom=cuentaFacade.find(t.getCuenta1().getCuentaPK());
				EstadoCuenta ec=null;
				if(cuentaFrom!=null){
					ec=estadoCuentaFacade.findCurrent(cuentaFrom);	
				}				
				r=validationData(cuentaFrom, ec,t);
				
				if(success.equals(r.getAuthorizerResponse())){
					BigDecimal newSaldo= ec.getSaldoactual().subtract(t.getValor());
					ec.setEstado(EstadosEstadoCuenta.TRANSACCIONES_PENDIENTES);
					ec.setSaldoactual(newSaldo);
					t.setReferencia(TransactionUtils.generateIntegerID());					
					t.setEstado(EstadosTransaccion.PROCESADA);
					
					estadoCuentaFacade.edit(ec);
					transaccionFacade.edit(t);					
				}
			}
			catch(Exception e){
				r.setResponseCode(AuthorizerResponse.ERROR);
				r.setMessage("Error.transaction.process");				
			}			
			
		}
		
		return r;
	}


}
