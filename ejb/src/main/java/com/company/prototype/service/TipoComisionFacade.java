/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.Date;

import com.company.prototype.model.entity.Log;
import com.company.prototype.model.entity.Moneda;
import com.company.prototype.model.entity.Tarjeta;
import com.company.prototype.model.entity.TipoComision;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class TipoComisionFacade extends AbstractFacade<TipoComision> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoComisionFacade() {
        super(TipoComision.class);
    }
    
    @Override
    public void create(TipoComision entity) {
    	entity.setAdmCreacion(new Date());
    	super.create(entity);
        
    }
    
    @Override
    public TipoComision edit(TipoComision entity) {
    	entity.setAdmActualizacion(new Date());
        return super.edit(entity);
    }
    
    /* datos específicos del objeto */
    protected Log toLog(TipoComision e){    	
    	Log log= new Log();
    	log.setReferencia(e.getId()!=null?e.getId().toString():"");
    	log.setUsuario(e.getAdmUsuario());    	
    	return log;    	
    }
    
}
