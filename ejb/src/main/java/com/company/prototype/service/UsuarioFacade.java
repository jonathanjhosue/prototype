/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.service;

import java.util.Date;

import com.company.prototype.model.entity.Moneda;
import com.company.prototype.model.entity.Usuario;
import com.company.prototype.util.UsersUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jonathan
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;
    
    private UsersUtil util = new UsersUtil();

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public void create(Usuario entity) {
    	entity.setAdmCreacion(new Date());
    	entity.setAdmActualizacion(new Date());
    	entity.setPassword(util.generateMD5Password(entity.getPassword()));
    	super.create(entity);
        
    }
    
    @Override
    public Usuario edit(Usuario entity) {
    	entity.setAdmActualizacion(new Date());
    	if(entity.getAdmCreacion()==null)
    		entity.setAdmCreacion(new Date());
    	entity.setPassword(util.generateMD5Password(entity.getPassword()));
        return super.edit(entity);
    }
    
}
