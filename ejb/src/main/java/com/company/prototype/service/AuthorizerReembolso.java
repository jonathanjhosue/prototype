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

@Named("authorizerReembolso")
@SessionScoped
public class AuthorizerReembolso implements Authorizer,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final String transactionTypeCode= TiposTransaccion.REEMBOLSO.getValue();

	final Validator v= new Validator();
	
	@EJB
	TarjetaFacade tarjetaFacade;
	
	@EJB
	CuentaFacade cuentaFacade;
	
	@EJB
	EstadoCuentaFacade estadoCuentaFacade;
	
	@EJB
	TransaccionFacade transaccionFacade;
	
	public AuthorizerReembolso(){
		
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
			
		}else if(v.isNullorEmpty(t.getIdentificacion())){//transactionId to refund
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidIdentification");			
		}
		else if(v.isNullorEmpty(t.getCuenta())){ // Cuenta From
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.AccountMissing");			
		}
		
		return r;
	}


	public AuthorizationResult validationData(Cuenta cuenta, EstadoCuenta ec,Transaccion tOrigen) {		
		AuthorizationResult r=new AuthorizationResult(AuthorizerResponse.DESCONOCIDO);
		
		if(v.isNullorEmpty(cuenta)){	//cuenta To		
			//consulta externa, no implementada			
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.AccountNotFound");
			
		}else if(v.isNullorEmpty(tOrigen)){	//cuenta To		
			//consulta externa, no implementada			
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.TransactionMissing");
			
		}else{ 
			if(!v.isTransactionToAvoid(tOrigen)){
				r.setResponseCode(AuthorizerResponse.INVALID_DATA);
				r.setMessage("Validations.Transaction.TransactionIsNotAvoidable");
			}
			else if(v.isNullorEmpty(ec)){	
				r.setResponseCode(AuthorizerResponse.INVALID_DATA);
				r.setMessage("Validations.Transaction.StateAccountNotFound");
			
			}else{				
				if(v.isValidStateToProcess(cuenta)){
					
					BigDecimal newSaldo=ec.getSaldoactual().subtract(tOrigen.getValor());
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
				Transaccion tOrigen=transaccionFacade.find(t.getIdentificacion());				
				r=validationData(cuentaFrom, ec,tOrigen);
				
				if(success.equals(r.getAuthorizerResponse())){
					BigDecimal newSaldo= ec.getSaldoactual().subtract(t.getValor());
					ec.setEstado(EstadosEstadoCuenta.TRANSACCIONES_PENDIENTES);
					//ec.setSaldoactual(newSaldo);
					t.setReferencia(EntityUtil.generateIntegerID());					
					t.setEstado(EstadosTransaccion.PROCESADA);
					
					tOrigen.setEstado(EstadosTransaccion.ANULADA);//se actualiza el estado en el proceso de cierre
					
					estadoCuentaFacade.edit(ec);
					transaccionFacade.edit(tOrigen);
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
