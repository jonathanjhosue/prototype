/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.Date;
import java.util.List;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.company.prototype.model.entity.Log;
import com.company.prototype.util.SimpleDBLogging;

/**
 *
 * @author jonathan
 */
public abstract class AbstractFacade<T> {
	
	@EJB
	private SimpleDBLogging log;
	
    private Class<T> entityClass;
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    protected abstract Log toLog(T entity);

    protected abstract EntityManager getEntityManager();
    
    private void logging(T entity, String strLog){
    	Log dbLog = toLog(entity);
    	dbLog.setFecha(new Date());
    	dbLog.setModelo(entity.getClass().getSimpleName());
    	//dbLog.setReferencia(modelo);
    	//dbLog.setUsuario(entity.);
    	dbLog.setLog(strLog);
    	
    	//SimpleDBLogging.getInstace()
    	log.addLog(dbLog);
    	
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
        logging(entity,"Create");
    }

    public T edit(T entity) {
    	T ent = getEntityManager().merge(entity);
    	logging(ent,"Save");
        return ent;
        
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    	logging(entity,"Remove");
    }

    public T find(Object id) {
    	try{
    		return getEntityManager().find(entityClass, id);
    	}catch(Exception e){
    		e.printStackTrace();    		
    	}
        return null;
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    
    /*protected  abstract void administrativeActivity(T entity);*/
    
}
