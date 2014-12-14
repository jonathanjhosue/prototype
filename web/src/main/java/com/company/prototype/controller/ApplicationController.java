package com.company.prototype.controller;


import com.company.prototype.util.ApplicationConfiguration;
import com.company.prototype.util.ApplicationConfiguration.EstadosEstandar;
import com.company.prototype.util.ApplicationConfiguration.TiposCliente;
import com.company.prototype.util.ApplicationConfiguration.TiposComision;
import com.company.prototype.util.ApplicationConfiguration.TiposCuenta;
import com.company.prototype.util.ApplicationConfiguration.TiposIdentificacion;
import com.company.prototype.util.ApplicationConfiguration.TiposTarjeta;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;


@Named("applicationController")
@ApplicationScoped
public class ApplicationController implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TiposIdentificacion[] getTiposIdentificacion() {
        return ApplicationConfiguration.TiposIdentificacion.values();
    }
    
    public TiposCliente[] getTiposCliente() {
        return ApplicationConfiguration.TiposCliente.values();
    }
    
    public TiposCuenta[] getTiposCuenta() {
        return ApplicationConfiguration.TiposCuenta.values();
    }
    
    public TiposTarjeta[] getTiposTarjeta() {
        return ApplicationConfiguration.TiposTarjeta.values();
    }
    
    public TiposComision[] getTiposComision() {
        return ApplicationConfiguration.TiposComision.values();
    }
    
    public EstadosEstandar[] getEstadosEstandar() {
        return ApplicationConfiguration.EstadosEstandar.values();
    }
    
    public List<String> getCountries(String filter) {
    	//java 8
    	return ApplicationConfiguration.getCountries().parallelStream().filter(c -> c.toLowerCase().indexOf(filter.toLowerCase()) >-1).collect(Collectors.toList());
    }
    
    public List<String> getCountries() {
        return ApplicationConfiguration.getCountries();
    }
    
    public List<String> getISOCountries(String filter) {
    	//java 8
    	return ApplicationConfiguration.getISOCountries().parallelStream().filter(c -> c.toLowerCase().indexOf(filter.toLowerCase()) >-1).collect(Collectors.toList());
    }
    

}
