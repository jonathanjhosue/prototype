/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import com.company.prototype.model.entity.Cierre;
import com.company.prototype.model.entity.Log;
import com.company.prototype.model.entity.Movimiento;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class MovimientoFacade extends AbstractFacade<Movimiento> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoFacade() {
        super(Movimiento.class);
    }
    
    /* datos específicos del objeto */
    protected Log toLog(Movimiento e){    	
    	Log log= new Log();
    	log.setReferencia(e.getId()!=null?e.getId().toString():"");    	
    	return log;    	
    }
    
}
