package com.company.prototype.service;

import java.io.Serializable;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.company.prototype.model.bean.AuthorizationResult;
import com.company.prototype.model.bean.Transaction;
import com.company.prototype.model.entity.Comercio;
import com.company.prototype.model.entity.Cuenta;
import com.company.prototype.model.entity.CuentaPK;
import com.company.prototype.model.entity.Entidad;
import com.company.prototype.model.entity.Moneda;
import com.company.prototype.model.entity.Tarjeta;
import com.company.prototype.model.entity.TipoCambio;
import com.company.prototype.model.entity.TipoCambioPK;
import com.company.prototype.model.entity.Transaccion;
import com.company.prototype.util.ApplicationConfiguration.AuthorizerResponse;
import com.company.prototype.util.ApplicationConfiguration.EstadosTransaccion;
import com.company.prototype.util.ApplicationConfiguration.TiposTransaccion;
import com.company.prototype.util.EntityUtil;
import com.company.prototype.util.TransactionUtils;
import com.company.prototype.util.Validator;


/*
 * Servicio que simula una autorización
 * para diferentes tipos de transacción
 * 
 * 
 * */

@Named("authorizerService")
@SessionScoped
//@Stateless
public class AuthorizerService implements Serializable{
	
	@EJB
    private com.company.prototype.service.TransaccionFacade ejbFacade;
	
	@EJB
    private com.company.prototype.service.EntidadFacade ejbEntidad;
	
	@EJB
    private com.company.prototype.service.TarjetaFacade ejbTarjeta;
	
	@EJB
    private com.company.prototype.service.CuentaFacade ejbCuenta;
	
	@EJB
    private com.company.prototype.service.ComercioFacade ejbComercio;
	
	@EJB
    private com.company.prototype.service.MonedaFacade ejbMoneda;
	
	@EJB
    private com.company.prototype.service.TipoCambioFacade ejbTipoCambio;
	
	@EJB
    private com.company.prototype.service.TransaccionFacade ejbTransaccion;
	
	@EJB
    private com.company.prototype.service.AuthorizerFactory authorizerFactory;
		
	public AuthorizerService(){
		
	}
	
	//@Inject BeanManager bm;
	

	
	public AuthorizationResult process(Transaction transactionBean){
		
		AuthorizationResult r=new AuthorizationResult(AuthorizerResponse.DESCONOCIDO);
		try{
			
			Transaccion t=convertFromTransactionBean(transactionBean);//conversión a Transacción
			t=ejbTransaccion.edit(t);	//se guarda		
			
			//obtener el tipo de autorizador
			Authorizer authorizer= authorizerFactory.getAuthorizer(TiposTransaccion.parseTiposTransaccion(t.getTipotransaccion()));
			
			r=authorizer.process(t);
			
			updateTransactionResponse(transactionBean, t);

			
		}catch(Exception e){
			e.printStackTrace();
			
			r.setAuthorizerResponse(AuthorizerResponse.ERROR);
			r.setMessage("Error.transaction.process");
		}
		r.setTransaccion(transactionBean);	
		return r;
	}
	
	
	private void autorizadorCompra(Transaccion t){
		
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
		
	}
	
	private Transaccion createTransaction(Transaction t){
		Transaccion newTransaction = new Transaccion();
		//newTransaction.set
		
		//getFacade().create(t);
		
		
		return newTransaction;
	}
	
	
	
	private void autorizadorDeposito(Transaccion t){
		
		//Estados de estados de cuenta -> Sin cambios-> con transacciones -> con movimientos -> Sin cambios 
		
		//Valida la transaccion
		//Valida con base de datos estado cuenta actual			
		//Cambia estado transaccion y estado cuenta
		//Envia respuesta
		
		//Paso 2 asynchrono
		//Genera movimientos para estadocuenta actual
		//Actualiza estado transacción y estado cuenta	
		
	}
	
	private TransaccionFacade getFacade() {
        return ejbFacade;
    }


	
	private void autorizadorTransferenciaInterna(Transaccion t){
		
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
		//Genera movimientos para estados de cuentas actual
		//Actualiza estado transacción y estado cuentas
		
	}
		
		
	private void autorizadorTransferenciaExterna(Transaccion t){
		
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
		//Genera movimientos para estados de cuentas actual
		//Actualiza estado transacción y estado cuentas
		
	}
	
	private void autorizadorSimpe(Transaccion t){
		
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
		//Genera movimientos para estados de cuentas actual
		//Actualiza estado transacción y estado cuentas
		
	}
		
		
	private void autorizadorReembolso(Transaccion t){
		
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
		//Genera movimientos para estados de cuentas actual
		//Actualiza estado transacción y estado cuentas
		
	}
	
	
	private void autorizadorDesconocido(Transaccion t){
		
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
		//Genera movimientos para estados de cuentas actual
		//Actualiza estado transacción y estado cuentas
		
	}
	
	   //las transacciones pueden ser de muchos tipos por lo que se 
	   //guardan aún si no tienen referencias y se tengan que guardar como inválidas
	   public Transaccion convertFromTransactionBean(Transaction t){
		   Transaccion newT= new Transaccion();	
		   
		   Validator v=new Validator();
		   
		   Entidad entidadFrom=null;
		   if(t.getFromEntity()!=null){
			   entidadFrom=ejbEntidad.findByReference(t.getFromEntity());
		   }
		   
		   Entidad entidadTo=null;
		   if(t.getToEntity()!=null){
			   entidadTo=ejbEntidad.findByReference(t.getToEntity());
		   }
		   
		   Tarjeta tarjeta=null;
		   
		   if(!v.isNullorEmpty(t.getCardNumber())){
			   tarjeta= ejbTarjeta.find(t.getCardNumber());
		   }
  
		   Cuenta cuentaFrom=null;
		   if(entidadFrom!=null && !v.isNullorEmpty(t.getFromAccount())){
			   CuentaPK cuentaFromPk = new CuentaPK();
			   cuentaFromPk.setEntidadId(new Long(entidadFrom.getId()));
			   cuentaFromPk.setNumero(t.getFromAccount());
			   cuentaFrom=ejbCuenta.find(cuentaFromPk);
		   }
		   
		   Cuenta cuentaTo=null;
		   if(entidadTo!=null && !v.isNullorEmpty(t.getToAccount())){
			   CuentaPK cuentaToPk = new CuentaPK();
			   cuentaToPk.setEntidadId(entidadTo.getId());
			   cuentaToPk.setNumero(t.getToAccount());
			   cuentaTo= ejbCuenta.find(cuentaToPk);
		   }
		   
		   Moneda monedaFrom = null;
		   if(cuentaFrom!=null){
			   monedaFrom= cuentaFrom.getMonedaId();
		   }
			  
		   Moneda monedaTo = null;
		   if(!v.isNullorEmpty(t.getCurrencyCode())){
			   monedaTo = ejbMoneda.find(t.getCurrencyCode());
		   }
		   
		   if(v.isNullorEmpty(monedaTo) && v.isNullorEmpty(cuentaTo)){
			   monedaTo= cuentaTo.getMonedaId();
		   }
		   
		   Comercio comercio= null;
		   if(!v.isNullorEmpty(t.getMerchandId())){
			   comercio=  ejbComercio.findByIdentification(t.getMerchandId());
		   }
		   
		   TipoCambio tipoCambio=null;
		   if(!v.isNullorEmpty(monedaFrom) && !v.isNullorEmpty(monedaTo)){
			   TipoCambioPK tipoCambioPk= new TipoCambioPK();
			   tipoCambioPk.setMonedaorigenId(monedaFrom.getId());
			   tipoCambioPk.setMonedadestinoId(monedaTo.getId());
			   tipoCambio = ejbTipoCambio.CurrentTipoCambioCached(tipoCambioPk);			   
		   }
		   	   
		   //TipoTransaccion tipoTransaccion = new TipoTransaccion();
		   TiposTransaccion tipoTransaccion = TiposTransaccion.parseTiposTransaccion(t.getTransactionType());
		   		  
		   //crear la transacción
		  
		   newT.setId(t.getId());
		   newT.setIdentificacion(t.getIdentification());		   
		   newT.setReferencia(EntityUtil.generateIntegerID());
		   newT.setFecha(t.getDate()!=null?t.getDate():Calendar.getInstance().getTime());
		   newT.setEstado(EstadosTransaccion.RECIBIDA.getValue());
		   newT.setValor(t.getValue());		   
		   newT.setTarjetaNumero(tarjeta);
		   newT.setCuenta(cuentaFrom);
		   newT.setCuenta1(cuentaTo);		   
		   newT.setComercioId(comercio);
		   newT.setMonedaId(monedaTo);
		   newT.setTipocambioId(tipoCambio);		   
		   
		   newT.setTipotransaccion(tipoTransaccion);		   
		   		   
		   return newT;
	   
}
	
	   
	   private void updateTransactionResponse(Transaction transactionBean, Transaccion t){
		   
		   transactionBean.setState(t.getEstado());
		   transactionBean.setReference(t.getReferencia());	   
		   
	   }
	

	
	
	

}
