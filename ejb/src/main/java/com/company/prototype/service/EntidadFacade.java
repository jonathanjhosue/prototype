/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.Date;

import com.company.prototype.model.entity.Cierre;
import com.company.prototype.model.entity.Entidad;
import com.company.prototype.model.entity.Log;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class EntidadFacade extends AbstractFacade<Entidad> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntidadFacade() {
        super(Entidad.class);
    }
    
    @Override
    public void create(Entidad entity) {
    	entity.setAdmCreacion(new Date());
    	entity.setAdmActualizacion(new Date());
    	super.create(entity);
        
    }
    
    @Override
    public Entidad edit(Entidad entity) {
    	entity.setAdmActualizacion(new Date());
    	if(entity.getAdmCreacion()==null){
    		entity.setAdmCreacion(new Date());
    	}    	
        return super.edit(entity);
    }
    
    public Entidad findByReference(String reference) { 
    	
    	try{
    		return em.createNamedQuery("Entidad.findByReferencia",Entidad.class)
    		.setParameter("referencia", reference)
    		.getSingleResult();
    		
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		
    	}
    	    	  	
        return null; 
    }
    
    /* datos específicos del objeto */
    protected Log toLog(Entidad e){    	
    	Log log= new Log();
    	log.setReferencia(e.getId()!=null?e.getId().toString():"");
    	log.setUsuario(e.getAdmUsuario());    	
    	return log;    	
    }
    
}
