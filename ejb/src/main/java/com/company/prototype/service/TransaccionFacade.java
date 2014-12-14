/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.List;

import com.company.prototype.model.entity.Comercio;
import com.company.prototype.model.entity.Transaccion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class TransaccionFacade extends AbstractFacade<Transaccion> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransaccionFacade() {
        super(Transaccion.class);
    }
    
    public List<Transaccion> findByComercio(Comercio comercio) {      
        
        try{
    		return em.createNamedQuery("Transaccion.findByComercio",Transaccion.class)
            		.setParameter("comercioId", comercio)
            		.getResultList();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		
    	}
    	return null;
    }
    
}
