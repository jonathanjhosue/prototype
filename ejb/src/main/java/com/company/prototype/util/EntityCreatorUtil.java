package com.company.prototype.util;

import com.company.prototype.model.entity.Cuenta;
import com.company.prototype.model.entity.CuentaPK;
import com.company.prototype.model.entity.Entidad;


/*
 * Clase para manejar estructuras de objetos en la creación de las 
 * diferentes entidades/datos 
 * que no se han guardado en la base de datos
 * es decir sin ids 
 * */
public class EntityCreatorUtil {
	
	
	public Cuenta createAccount(String accountNumber, String entityReference){
		

		Cuenta n= new Cuenta();

		
		return n;
		
	}
	
	
	public Entidad createEntity(String accountNumber, String reference){
		Entidad e= new Entidad();

		e.setReferencia(reference);
		
		return e;
		
	}
	

}
