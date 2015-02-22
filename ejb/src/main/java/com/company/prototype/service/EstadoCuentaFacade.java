/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.List;

import com.company.prototype.model.entity.Cierre;
import com.company.prototype.model.entity.Cuenta;
import com.company.prototype.model.entity.CuentaPK;
import com.company.prototype.model.entity.EstadoCuenta;
import com.company.prototype.model.entity.Log;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class EstadoCuentaFacade extends AbstractFacade<EstadoCuenta> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoCuentaFacade() {
        super(EstadoCuenta.class);
    }
    
    public EstadoCuenta findCurrent(Cuenta cuenta) {    
    	

        return em.createNamedQuery("EstadoCuenta.findCurrent",EstadoCuenta.class)
        		.setParameter("cuenta", cuenta)
        		.setFirstResult(0)
        		.setMaxResults(1)
        		.getSingleResult();
    }
    
 public List<EstadoCuenta> findByCuenta(Cuenta cuenta) {    
        return em.createNamedQuery("EstadoCuenta.findCurrent",EstadoCuenta.class)
        		.setParameter("cuenta", cuenta)
        		.getResultList();
    }
 
 /* datos específicos del objeto */
 protected Log toLog(EstadoCuenta e){    	
 	Log log= new Log();
 	log.setReferencia(e.getId()!=null?e.getId().toString():""); 	
 	return log;    	
 }
    
    
}
