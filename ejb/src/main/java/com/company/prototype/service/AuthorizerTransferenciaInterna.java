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
import com.company.prototype.util.EntityUtil;
import com.company.prototype.util.TransactionUtils;
import com.company.prototype.util.Validator;
import com.company.prototype.util.ApplicationConfiguration.AuthorizerResponse;
import com.company.prototype.util.ApplicationConfiguration.EstadosEstadoCuenta;
import com.company.prototype.util.ApplicationConfiguration.EstadosTransaccion;
import com.company.prototype.util.ApplicationConfiguration.TiposTransaccion;

@Named("authorizerTransferenciaInterna")
@SessionScoped
public class AuthorizerTransferenciaInterna implements Authorizer,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final String transactionTypeCode= TiposTransaccion.TRANSFERENCIA_INTERNA.getValue();

	final Validator v= new Validator();
	
	@EJB
	TarjetaFacade tarjetaFacade;
	
	@EJB
	CuentaFacade cuentaFacade;
	
	@EJB
	EstadoCuentaFacade estadoCuentaFacade;
	
	@EJB
	TransaccionFacade transaccionFacade;
	
	public AuthorizerTransferenciaInterna(){
		
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
			r.setMessage("Validations.Transaction.AccountFromMissing");			
		}
		else if(v.isNullorEmpty(t.getCuenta1())){ // Cuenta To
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.AccountToMissing");
			
		}else if(v.isNullorEmpty(t.getMonedaId())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidCurrency");
			
		}else if(v.isNullorEmpty(t.getValor())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidValue");
			
		}else if(!t.getCuenta().getCuentaPK().getEntidadId().equals(t.getCuenta1().getCuentaPK().getEntidadId())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.ExternalAccount");			
		}
		else if(t.getValor().compareTo(BigDecimal.ZERO)<=0){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidValue");			
		}
		
		return r;
	}


	public AuthorizationResult validationData(Cuenta cuentaFrom,EstadoCuenta ecFrom,Cuenta cuentaTo,EstadoCuenta ecTo, Transaccion t) {		
		AuthorizationResult r=new AuthorizationResult(AuthorizerResponse.DESCONOCIDO);
		
		if(v.isNullorEmpty(cuentaFrom)){	//cuenta To		
			//consulta externa, no implementada			
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.AccountFromNotFound");
			
		}else if(v.isNullorEmpty(cuentaTo)){	//cuenta To		
			//consulta externa, no implementada			
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.AccountToFromNotFound");
			
		}
		else{
			if(v.isNullorEmpty(ecFrom)){	
				r.setResponseCode(AuthorizerResponse.INVALID_DATA);
				r.setMessage("Validations.Transaction.StateAccountFromNotFound");
			}else if(v.isNullorEmpty(ecTo)){	
				r.setResponseCode(AuthorizerResponse.INVALID_DATA);
				r.setMessage("Validations.Transaction.StateAccountToNotFound");
			}
			else{	
				//cuentaTo
				if(v.isValidStateToProcess(cuentaFrom) && v.isValidStateToProcess(cuentaTo)){
					BigDecimal newSaldo=ecFrom.getSaldoactual().subtract(t.getValor());
					if(newSaldo.compareTo(BigDecimal.ZERO)<0){					
						r.setResponseCode(AuthorizerResponse.REJECTED);
						r.setMessage("Validations.Transaction.InsufficientFunds");				
					}else{
						r.setResponseCode(AuthorizerResponse.SUCCESSFUL);					
					}
					r.setResponseCode(AuthorizerResponse.SUCCESSFUL);			
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
				Cuenta cuentaFrom=cuentaFacade.find(t.getCuenta().getCuentaPK());
				EstadoCuenta ecFrom=null;
				if(cuentaFrom!=null){
					ecFrom=estadoCuentaFacade.findCurrent(cuentaFrom);	
				}				
				
				Cuenta cuentaTo=cuentaFacade.find(t.getCuenta1().getCuentaPK());
				EstadoCuenta ecTo=null;
				if(cuentaTo!=null){
					ecTo=estadoCuentaFacade.findCurrent(cuentaTo);	
				}
				r=validationData(cuentaFrom, ecFrom,cuentaTo, ecTo,t);
				
				if(success.equals(r.getAuthorizerResponse())){
					
					BigDecimal newSaldoFrom= ecFrom.getSaldoactual().subtract(t.getValor());
					ecFrom.setEstado(EstadosEstadoCuenta.TRANSACCIONES_PENDIENTES);
					ecFrom.setSaldoactual(newSaldoFrom);
					
					BigDecimal newSaldoTo=ecTo.getSaldoactual().add(t.getValor());
					ecTo.setEstado(EstadosEstadoCuenta.TRANSACCIONES_PENDIENTES);
					ecTo.setSaldoactual(newSaldoTo);
					
					t.setReferencia(EntityUtil.generateIntegerID());					
					t.setEstado(EstadosTransaccion.PROCESADA);				
					
					estadoCuentaFacade.edit(ecFrom);
					estadoCuentaFacade.edit(ecTo);
					
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
