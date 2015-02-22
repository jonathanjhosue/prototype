/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.Date;

import com.company.prototype.model.entity.Cierre;
import com.company.prototype.model.entity.Cuenta;
import com.company.prototype.model.entity.Log;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class LogFacade extends AbstractFacade<Log> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogFacade() {
    	
        super(Log.class);
    }
    
    /*not log logs*/
    @Override
    public void create(Log entity) {
    	getEntityManager().persist(entity);        
    }
    
    /*not log logs*/
    @Override
    public Log edit(Log entity) {
    	return getEntityManager().merge(entity);
    }
    
    
    /* datos específicos del objeto */
    protected Log toLog(Log e){    	
    	return e;    	
    }
    
}
