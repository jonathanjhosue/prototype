package com.company.prototype.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.company.prototype.model.bean.Client;
import com.company.prototype.model.entity.Cliente;
import com.company.prototype.model.entity.Entidad;
import com.company.prototype.util.EntityBeanConverter;

@Stateless
public class ClientService {
	
	private EntityBeanConverter converter= new EntityBeanConverter();
	
	@EJB
	ClienteFacade clienteFacade;
	
	@EJB
	EntidadFacade entidadFacade;
	
	
	public Client getClient(String entityRef, String clientId){
		
		Entidad entidad= entidadFacade.findByReference(entityRef);
		if(entidad!=null){
			Cliente c=clienteFacade.findByIdentification(clientId, entidad);
			
			if(c!=null){
				return converter.convertClienteToClient(c);
			}	
		}		
						
		return null;		
	}
	
	public Client getClientTest(){
		
		Client client= new Client();
		client.setId(123l);
		client.setIdentity("12345");
		client.setCity("city");
		client.setCountry("Country");
		client.setContact("email@co.com;56451531;another;");
		client.setName("Name LastName");
		client.setClientType("ABC");
				
		return client;
		
	}
	
	

}
