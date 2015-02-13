package com.company.prototype.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.company.prototype.model.bean.AuthorizationResult;
import com.company.prototype.model.bean.Transaction;
import com.company.prototype.model.entity.Cuenta;
import com.company.prototype.model.entity.CuentaPK;
import com.company.prototype.model.entity.Entidad;
import com.company.prototype.model.entity.EstadoCuenta;
import com.company.prototype.model.entity.Tarjeta;
import com.company.prototype.model.entity.Transaccion;
import com.company.prototype.util.ApplicationConfiguration.AuthorizerResponse;
import com.company.prototype.util.ApplicationConfiguration.EstadosEstadoCuenta;
import com.company.prototype.util.ApplicationConfiguration.EstadosTransaccion;
import com.company.prototype.util.ApplicationConfiguration.TiposTransaccion;
import com.company.prototype.util.EntityUtil;
import com.company.prototype.util.TransactionUtils;
import com.company.prototype.util.Validator;

@Named("authorizerCompras")
@SessionScoped
//@Stateless
public class AuthorizerCompras implements Authorizer,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final String transactionTypeCode= TiposTransaccion.COMPRA.getValue();
	
	final Validator v= new Validator();
	
	@EJB
	TarjetaFacade tarjetaFacade;
	
	@EJB
	CuentaFacade cuentaFacade;
	
	@EJB
	EstadoCuentaFacade estadoCuentaFacade;
	
	@EJB
	TransaccionFacade transaccionFacade;
	
	public AuthorizerCompras(){
		
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
			
		}/*else if(v.isNullorEmpty(t.getReferencia())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidReference");
		
		}*/else if(v.isNullorEmpty(t.getComercioId())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidMerchant");
			
		}else if(v.isNullorEmpty(t.getTarjetaNumero())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidCard");
			
		}else if(v.isValid(t.getTarjetaNumero())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidCardNumber");
			
		}else if(v.isNullorEmpty(t.getMonedaId())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidCurrency");
			
		}else if(v.isNullorEmpty(t.getValor())){
			r.setResponseCode(AuthorizerResponse.INVALID_DATA);
			r.setMessage("Validations.Transaction.InvalidValue");
			
		}
		
		return r;
	}


	public AuthorizationResult validationData(Cuenta cuenta, Tarjeta tarjeta, EstadoCuenta ec,Transaccion t) {		
		AuthorizationResult r=new AuthorizationResult(AuthorizerResponse.DESCONOCIDO);
		
		if(v.isNullorEmpty(cuenta)){			
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
		
		
		//Estados de estados de cuenta -> Sin cambios-> con transacciones -> con movimientos -> Sin cambios 
		
		//Valida la transaccion
		//Valida con base de datos estado cuenta actual,
		//		Si es sin cambios       -> continua
		//		si es con transacciones -> Se verifica tabla transacciones, 
		//		si es con movimientos   -> Se verifica movimientos
		//			
		//Cambia estado transaccion y estado cuenta
		//Envia respuesta
		
		//Paso 2 asynchrono
		//Genera movimientos para estadocuenta actual
		//Actualiza estado transacción y estado cuenta	
		
		
		
		return r;
	}


	@Override
	public AuthorizationResult process(Transaccion t) {
		final AuthorizerResponse success=AuthorizerResponse.SUCCESSFUL;
		AuthorizationResult r=new AuthorizationResult(AuthorizerResponse.DESCONOCIDO);
		
		r=validation(t);
		if(success.equals(r.getAuthorizerResponse())){			
			try{
				String tarjetaNumber=t.getTarjetaNumero().getNumero();
				Tarjeta tarjeta= tarjetaFacade.find(tarjetaNumber);		
				Cuenta cuenta=cuentaFacade.findByTarjeta(tarjetaNumber);
				EstadoCuenta ec=null;
				if(cuenta!=null){
					ec=estadoCuentaFacade.findCurrent(cuenta);	
				}
				
				r=validationData(cuenta, tarjeta, ec,t);
				
				if(success.equals(r.getAuthorizerResponse())){
					BigDecimal newSaldo= ec.getSaldoactual().subtract(t.getValor());
					ec.setEstado(EstadosEstadoCuenta.TRANSACCIONES_PENDIENTES);
					ec.setSaldoactual(newSaldo);
					t.setReferencia(EntityUtil.generateIntegerID());					
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
