package com.company.prototype.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.company.prototype.model.bean.Account;
import com.company.prototype.model.bean.Card;
import com.company.prototype.model.bean.Exchange;
import com.company.prototype.model.bean.Movement;
import com.company.prototype.model.bean.StateAccount;
import com.company.prototype.model.entity.Cuenta;
import com.company.prototype.model.entity.CuentaPK;
import com.company.prototype.model.entity.Entidad;
import com.company.prototype.model.entity.EstadoCuenta;
//import com.company.prototype.util.ApplicationConfiguration.EstadosEstadoCuenta;
import com.company.prototype.model.entity.Movimiento;
import com.company.prototype.model.entity.TipoCambio;
import com.company.prototype.util.EntityBeanConverter;

@Stateless
public class AccountService {
	
	private EntityBeanConverter converter= new EntityBeanConverter();
	
	@EJB
	private CuentaFacade accountFacade;
	
	@EJB
	private EntidadFacade entidadFacade;
	
	
	public Account getAccountTest(){
		Account a=new Account();
		a.setAccountNumber("1561555664");
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(2014, 10, 1, 0, 0, 0);
		Date date = cal.getTime(); // get back a Date object
		
		StateAccount sa=new StateAccount();		
		sa.setAccountNumber("123456");
		sa.setDate(date);
		sa.setCurrentBalance(new BigDecimal("1000.00"));
		sa.setLastBalance(new BigDecimal("680.00"));		
		sa.setState("A");
		
		Exchange e= new Exchange();
		e.setCurrencyFrom("CRC");
		e.setValue(BigDecimal.ONE);
		e.setCurrencyTo("CRC");
		
		Movement m1= new Movement();
		m1.setId(100l);
		m1.setDate(date);
		m1.setDescription("Description 1");
		m1.setExchange(e);
		m1.setType("001");
		m1.setValue(new BigDecimal("523.22"));
		
		Movement m2= new Movement();
		m2.setId(101l);
		m2.setDate(date);
		m2.setDescription("Description 2");
		m2.setExchange(e);
		m2.setType("002");
		m2.setValue(new BigDecimal("524.23"));
		
		Movement m3= new Movement();
		m3.setId(102l);
		m3.setDate(date);
		m3.setDescription("Description 3");
		m3.setExchange(e);
		m3.setType("001");
		m3.setValue(new BigDecimal("525.22"));
		
		sa.getMovements().add(m1);
		sa.getMovements().add(m2);
		sa.getMovements().add(m3);
		
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(0);
		cal2.set(2014, 11, 1, 0, 0, 0);
		Date date2 = cal.getTime();
		
		StateAccount sa1=new StateAccount();		
		sa1.setAccountNumber("123456");
		sa1.setDate(date2);
		sa1.setCurrentBalance(new BigDecimal("985"));
		sa1.setLastBalance(new BigDecimal("678"));		
		sa1.setState("I");
		
		
		Movement m4= new Movement();
		m4.setId(100l);
		m4.setDate(date);
		m4.setDescription("Description 4");
		m4.setExchange(e);
		m4.setType("001");
		m4.setValue(new BigDecimal("521.22"));
		
		Movement m5= new Movement();
		m5.setId(101l);
		m5.setDate(date2);
		m5.setDescription("Description 5");
		m5.setExchange(e);
		m5.setType("002");
		m5.setValue(new BigDecimal("624.23"));
		
		Movement m6= new Movement();
		m6.setId(102l);
		m6.setDate(date2);
		m6.setDescription("Description 6");
		m6.setExchange(e);
		m6.setType("001");
		m6.setValue(new BigDecimal("625.22"));
		
		sa1.getMovements().add(m4);
		sa1.getMovements().add(m5);
		sa1.getMovements().add(m6);			
		
		a.getStateAccounts().add(sa);
		a.getStateAccounts().add(sa1);
		
		Card c= new Card();
		c.setNumber("44153454354");
		c.setBrand("Visa");		
		c.setExpirationDate(new Date());
		c.setLast4("1234");
		c.setType("Credit");
		
		Card c1= new Card();
		c1.setNumber("15153454354");	
		c1.setBrand("Mastercard");		
		c1.setExpirationDate(new Date());
		c1.setLast4("1234");
		c1.setType("Credit");
		
		a.getCards().add(c);
		a.getCards().add(c1);
		
		
		return a;
	}
	
	
	public Account getAccount(String entityId, String accountNumber ){
		
		Entidad entidad= entidadFacade.findByReference(entityId);		
		if(entidad==null){
			return null;
		}
		
		CuentaPK pk= new CuentaPK(accountNumber, entidad.getId());		
		Cuenta cuenta= accountFacade.find(pk);	
		if(cuenta==null){
			return null;
		}
					
		
		return converter.convertCuentaToAccount(cuenta);
				
	}	
	
	

}
