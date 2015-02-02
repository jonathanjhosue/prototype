/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.Date;
import java.util.List;

import com.company.prototype.model.entity.Comercio;
import com.company.prototype.model.entity.Cuenta;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }
    
    @Override
    public void create(Cuenta entity) {
    	entity.setAdmCreacion(new Date());
    	entity.setAdmActualizacion(new Date());
    	super.create(entity);
        
    }
    
    @Override
    public Cuenta edit(Cuenta entity) {
    	entity.setAdmActualizacion(new Date());
    	if(entity.getAdmCreacion()==null){
    		entity.setAdmCreacion(new Date());
    	}
        return super.edit(entity);
    }
    
    
    public Cuenta findByTarjeta(String numeroTarjeta) {        
        return em.createNamedQuery("Cuenta.findByTarjeta",Cuenta.class)
        		.setParameter("numeroTarjeta", numeroTarjeta)
        		.getSingleResult();
    }
    
    
    
}
