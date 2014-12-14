package com.company.prototype.util;


import java.math.BigDecimal;

import com.company.prototype.model.entity.Comercio;
import com.company.prototype.model.entity.Cuenta;
import com.company.prototype.model.entity.CuentaPK;
import com.company.prototype.model.entity.EstadoCuenta;
import com.company.prototype.model.entity.Moneda;
import com.company.prototype.model.entity.Tarjeta;
import com.company.prototype.model.entity.Transaccion;
import com.company.prototype.util.ApplicationConfiguration.EstadosEstadoCuenta;
import com.company.prototype.util.ApplicationConfiguration.EstadosTransaccion;
import com.company.prototype.util.ApplicationConfiguration.TiposTransaccion;

public class Validator {
	
	private final String visaExpression=           "\\b(4[0-9]{12}(?:[0-9]{3})?)\\b";     	// Visa
	private final String masterCardExpression=     "\\b(5[1-5][0-9]{14})\\b";  				// Mastercard
	private final String americanExpressExpression="\\b(3[47][0-9]{13})\\b";  				// American Express
	private final String dinersClubExpression=     "^\\b(3(?:0[0-5]|[68][0-9])[0-9]{11})\\b"; // Diners Club
	private final String discoverExpression=       "\\b(6(?:011|5[0-9]{2})[0-9]{12})\\b";  	// Discover
	private final String jcbExpression=            "\\b((?:2131|1800|35\\d{3})\\d{11})\\b"; // JCB
	


	public boolean isNullorEmpty(BigDecimal v){
		return v==null || v.compareTo(BigDecimal.ZERO) == 0;
		
	}
	
	public boolean isNullorEmpty(String v){
		return v==null || v.isEmpty();
		
	}
	
	public boolean isNullorEmpty(Long v){
		return v==null || v.longValue()==0L;		
	}
	
	
	public boolean isNullorEmpty(Comercio v){
		return v==null || v.getId()==null || v.getId().longValue()==0L;		
	}
	
	public boolean isNullorEmpty(Tarjeta v){
		return v==null || v.getNumero()==null || v.getNumero().isEmpty();		
	}
	
	public boolean isNullorEmpty(Moneda v){
		return v==null || v.getId()==null || v.getId().isEmpty();		
	}
	
	public boolean isNullorEmpty(Cuenta v){
		return v==null || isNullorEmpty(v.getCuentaPK());		
	}
	
	public boolean isNullorEmpty(CuentaPK v){
		return v==null || v.getNumero()==null || v.getEntidadId()==null || 
			   v.getNumero().isEmpty() || v.getEntidadId().isEmpty();		
	}
	
	public boolean isNullorEmpty(EstadoCuenta v){
		return v==null || isNullorEmpty(v.getCuenta()) || v.getFecha()==null;		
	}
	
	public boolean isNullorEmpty(Transaccion v){
		return v==null || isNullorEmpty(v.getId());		
	}
	
	
	public boolean isValid(Tarjeta t){
		boolean r=false;
		
		if(!isNullorEmpty(t)){
			r=isValidCreditCard(t.getNumero());
		}		
		return r;
	}
	
	public boolean isValidCreditCard(String t){
		boolean r=false;
		if(!isNullorEmpty(t)){
			r=t.matches(visaExpression)       || t.matches(masterCardExpression) || t.matches(americanExpressExpression) ||
			  t.matches(dinersClubExpression) || t.matches(discoverExpression)   || t.matches(jcbExpression);
		}		
		return r;
	}
	
	public boolean isVisa(String t){
		return isValidCreditCard(t, visaExpression);
	}
	public boolean isMasterCard(String t){
		return isValidCreditCard(t, masterCardExpression);
	}
	public boolean isAmericanExpress(String t){
		return isValidCreditCard(t, americanExpressExpression);
	}
	public boolean isDinersClub(String t){
		return isValidCreditCard(t, dinersClubExpression);
	}
	public boolean isDiscover(String t){
		return isValidCreditCard(t, discoverExpression);
	}
	public boolean isJcb(String t){
		return isValidCreditCard(t, jcbExpression);
	}
	
	private boolean isValidCreditCard(String t, String expression){
		boolean r=false;
		if(!isNullorEmpty(t)){
			r=t.matches(expression);			
		}		
		return r;
	}
	
	
	//
	public boolean isValidStateToProcess(Cuenta c){
		return c.getEstado().equals(EstadosEstadoCuenta.ACTIVO.getValue()) || 
		       c.getEstado().equals(EstadosEstadoCuenta.TRANSACCIONES_PENDIENTES.getValue());
		
	}
	
	
	public boolean isTransactionToAvoid(Transaccion t){
		return t.getTipotransaccion().equals(TiposTransaccion.COMPRA) &&
			   !t.getEstado().equals(EstadosTransaccion.ANULADA.getValue()) && 
		       !t.getEstado().equals(EstadosTransaccion.DESCONOCIDO.getValue()) &&
		       !t.getEstado().equals(EstadosTransaccion.RECHAZADA.getValue());
		
	}




}
