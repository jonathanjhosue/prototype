/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.Date;

import com.company.prototype.model.entity.Cuenta;
import com.company.prototype.model.entity.Tarjeta;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class TarjetaFacade extends AbstractFacade<Tarjeta> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetaFacade() {
        super(Tarjeta.class);
    }
    
    
    @Override
    public void create(Tarjeta entity) {
    	entity.setAdmCreacion(new Date());
    	entity.setAdmActualizacion(new Date());
    	super.create(entity);
        
    }
    
    @Override
    public Tarjeta edit(Tarjeta entity) {
    	entity.setAdmActualizacion(new Date());
    	if(entity.getAdmCreacion()==null){
    		entity.setAdmCreacion(new Date());
    	}
        return super.edit(entity);
    }
    
    
}
