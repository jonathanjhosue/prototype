package com.company.prototype.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.company.prototype.model.bean.Account;
import com.company.prototype.model.bean.Card;
import com.company.prototype.model.bean.Client;
import com.company.prototype.model.bean.Exchange;
import com.company.prototype.model.bean.Movement;
import com.company.prototype.model.bean.StateAccount;
import com.company.prototype.model.entity.Cliente;
import com.company.prototype.model.entity.Cuenta;
import com.company.prototype.model.entity.EstadoCuenta;
import com.company.prototype.model.entity.Movimiento;
import com.company.prototype.model.entity.Tarjeta;
import com.company.prototype.model.entity.TipoCambio;


public class EntityBeanConverter {
	
	
	public Account convertCuentaToAccount(Cuenta c){
		
		Account a= new Account();		
		a.setAccountNumber(c.getCuentaPK().getNumero());
		
		a.setStateAccounts(convertEstadoCuentaToStateAccount(c.getEstadoCuentaList()));
		a.setCards(convertTarjetaToCard(c.getTarjetaList()));
		
		return a;
	}
	
	public Movement convertMovimientoToMovement(Movimiento m){
		
		Movement v= new Movement();	
		
		v.setId(m.getId());
		v.setDate(m.getFecha());
		v.setDescription(m.getDescripcion());
		v.setExchange(convertTipoCambioToExchange(m.getTipocambioId()));
		v.setType(m.getTipoMovimiento());
		v.setValue(new BigDecimal(m.getValor()));
		
		return v;
	}
	
	
	public Exchange convertTipoCambioToExchange(TipoCambio t){
		Exchange e= new Exchange();
		e.setCurrencyFrom(t.getMoneda1().getId());
		e.setCurrencyTo(t.getMoneda2().getId());
		e.setValue(t.getValor());
		
		return e;
	}
	
	public StateAccount convertEstadoCuentaToStateAccount(EstadoCuenta ec){
		StateAccount as=new StateAccount();
		as.setAccountNumber(ec.getCuenta().getCuentaPK().getNumero());
		as.setCurrentBalance(ec.getSaldoactual());
		as.setDate(ec.getFecha());
		as.setLastBalance(ec.getSaldoanterior());
		as.setState(ec.getEstado());
		as.setMovements(convertMovimientoToMovement(ec.getMovimientoList()));
		
		return as;
		
	}
	
	
	public List<StateAccount>  convertEstadoCuentaToStateAccount(List<EstadoCuenta> list){
		List<StateAccount> listOut= new ArrayList<StateAccount>();
		list.forEach(e -> {
			listOut.add(convertEstadoCuentaToStateAccount(e));			  
			});
		
		return listOut;
		
	}
	
	public Card convertTarjetaToCard(Tarjeta t){
		Card c= new Card();
		
		c.setBrand(t.getMarca());
		c.setExpirationDate(t.getFechaVencimiento());
		c.setLast4(t.getUltimos4());
		c.setNumber(t.getNumero());
		c.setType(t.getTipoTarjeta());
		
		return c;
		
	}
	
	public List<Card> convertTarjetaToCard(List<Tarjeta> list){
		//JAVA 8
		List<Card> listOut= new ArrayList<Card>();
		list.forEach(e -> {
			listOut.add(convertTarjetaToCard(e));			  
			});
			
		return listOut;
	}
	
	/*public Currency monedaToCurrency(Moneda m){
		Currency c= new Currency();
		c.se
		
		
		return c
	}*/
	
	
	
	public List<Movement> convertMovimientoToMovement(List<Movimiento> list){
		//JAVA 8
		List<Movement> listOut= new ArrayList<Movement>();
		list.forEach(e -> {
			listOut.add(convertMovimientoToMovement(e));			  
			});
			
		return listOut;
	}
	
	
	public Client convertClienteToClient(Cliente c){
		Client v= new Client();
		
		v.setAddress(c.getDireccion());
		v.setCity(c.getCiudad());
		v.setClientType(c.getTipoCliente());
		v.setContact(c.getContactos());
		v.setCountry(c.getPais());
		v.setId(c.getId());
		v.setIdentity(c.getIdentificacion());
		v.setIdentityType(c.getTipoIdentificacion());
		v.setName(c.getNombre());
		
		return v;
		
	}
	
	

}
