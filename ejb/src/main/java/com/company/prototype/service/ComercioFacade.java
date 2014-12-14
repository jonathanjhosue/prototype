/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.Date;

import com.company.prototype.model.entity.Comercio;
import com.company.prototype.model.entity.Entidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class ComercioFacade extends AbstractFacade<Comercio> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComercioFacade() {
        super(Comercio.class);
    }
    
    @Override
    public void create(Comercio entity) {
    	entity.setAdmCreacion(new Date());
    	super.create(entity);
        
    }
    
    @Override
    public Comercio edit(Comercio entity) {
    	entity.setAdmActualizacion(new Date());
        return super.edit(entity);
    }
    
    public Comercio findByIdentification(String identification) {        
    	
    	try{
    		return em.createNamedQuery("Comercio.findByIdentificacion",Comercio.class)
            		.setParameter("identificacion", identification)
            		.getSingleResult();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		
    	}
    	return null;
        
    }
    
}
