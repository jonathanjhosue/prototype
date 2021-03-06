/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.Date;

import com.company.prototype.model.entity.Cierre;
import com.company.prototype.model.entity.Log;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class CierreFacade extends AbstractFacade<Cierre> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CierreFacade() {
        super(Cierre.class);
    }
    
    /* datos específicos del objeto */
    protected Log toLog(Cierre e){    	
    	Log log= new Log();
    	log.setReferencia(e.getId()!=null?e.getId().toString():"");
    	log.setUsuario(e.getAdmUsuario());    	
    	return log;    	
    }
    
}
