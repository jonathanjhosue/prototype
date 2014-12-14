package com.company.prototype.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.company.prototype.model.entity.Tarjeta;


public class ValidatorTest {
	Validator v =new Validator();
	
	@Test
	public void isValidCard(){
		
		Tarjeta t = new Tarjeta();
		
		t.setNumero("878299246310005");		
		assertTrue(!v.isValid(t));
		
		t.setNumero("4716838474205945");
		assertTrue(v.isValid(t));
		
		assertTrue( v.isVisa("4012888888881881"));
		assertTrue( v.isVisa("4111111111111111"));
		
		assertTrue( v.isMasterCard("5105105105105100"));
		assertTrue( v.isMasterCard("5555555555554444"));
		
		assertTrue( v.isAmericanExpress("371449635398431"));
		assertTrue( v.isAmericanExpress("378734493671000"));
		
		assertTrue( v.isDinersClub("30569309025904"));
		assertTrue( v.isDinersClub("38520000023237"));
		
		assertTrue( v.isDiscover("6011111111111117"));
		assertTrue( v.isDiscover("6011000990139424"));
		
		assertTrue( v.isJcb("3530111333300000"));
		assertTrue( v.isJcb("3566002020360505"));
				
		
	}
	
	

}
