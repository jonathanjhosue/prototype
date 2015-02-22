/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.Date;

import com.company.prototype.model.entity.Entidad;
import com.company.prototype.model.entity.Log;
import com.company.prototype.model.entity.Tarjeta;
import com.company.prototype.model.entity.TipoCambio;
import com.company.prototype.model.entity.TipoCambioPK;
import com.company.prototype.util.EntityUtil;
import com.company.prototype.util.SimpleCache;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.infinispan.manager.CacheContainer;

/**
 *
 * @author jonathan
 */
@Stateless
public class TipoCambioFacade extends AbstractFacade<TipoCambio> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;
    
    @Resource(lookup="java:jboss/infinispan/container/prototypeCache")
    private CacheContainer container; 
    
    final String idSeparator="||";
    private final String cacheName="cacheCurrentExchange";    
    private org.infinispan.Cache<String, TipoCambio> cache;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCambioFacade() {
        super(TipoCambio.class);
        //
    }
    
    @PostConstruct  
    public void initCache() {

    	cache=container.getCache(cacheName);


   	}

	 @Override
	    public void create(TipoCambio entity) {
		 	entity.setId(EntityUtil.nextId());
	    	entity.setAdmCreacion(new Date());
	    	super.create(entity);
	        
	    }
	    
	    @Override
	    public TipoCambio edit(TipoCambio entity) {
	    	if(entity.getId()==null || entity.getId().longValue()==0l){
	    		entity.setId(EntityUtil.nextId());
	    	}
	    	if(entity.getAdmCreacion()==null){
	    		entity.setAdmCreacion(new Date());
	    	}
	        return super.edit(entity);
	    }
    
	    public TipoCambio findCurrent(TipoCambioPK tipoCambioPk) {
	    	    	
	    	try{
	    		return em.createNamedQuery("TipoCambio.findCurrent",TipoCambio.class)
	        		.setParameter("monedaorigenId", tipoCambioPk.getMonedaorigenId())
	        		.setParameter("monedadestinoId", tipoCambioPk.getMonedadestinoId())
	        		.getSingleResult();
	    	}catch(Exception e){
	    		e.printStackTrace();	    		
	    	}
	    	return null;
	    }
	    
	    public TipoCambio CurrentTipoCambioCached (TipoCambioPK tipoCambioPk){
	    	
	    	//El cache también puede ser administrado por el cache nivel 2 de JPA/Hibernate
	    	//Se realiza de manera manual para referencia
	    	String id=tipoCambioPk.getMonedaorigenId()+idSeparator+tipoCambioPk.getMonedadestinoId();
	    	TipoCambio tipoCambio=cache.get(id);
	    	if(tipoCambio==null){
	    		tipoCambio= this.findCurrent(tipoCambioPk);
	    		if(tipoCambio!=null){
	    			cache.put(id, tipoCambio);
	    		}
	    	}
	    	
	    	return tipoCambio;
	    	
	    }
	    
	    /* datos específicos del objeto */
	    protected Log toLog(TipoCambio e){    	
	    	Log log= new Log();
	    	log.setReferencia(e.getId()!=null?e.getId().toString():"");
	    	log.setUsuario(e.getAdmUsuario());    	
	    	return log;    	
	    }
    
}
