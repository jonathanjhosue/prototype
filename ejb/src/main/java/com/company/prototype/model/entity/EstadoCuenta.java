/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.company.prototype.util.ApplicationConfiguration.EstadosEstadoCuenta;

/**
 *
 * @author jonathan
 */
@Entity
@Table(name = "EstadoCuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCuenta.findAll", query = "SELECT e FROM EstadoCuenta e"),
    @NamedQuery(name = "EstadoCuenta.findById", query = "SELECT e FROM EstadoCuenta e WHERE e.id = :id"),
    @NamedQuery(name = "EstadoCuenta.findByFecha", query = "SELECT e FROM EstadoCuenta e WHERE e.fecha = :fecha"),
	@NamedQuery(name = "EstadoCuenta.findCurrent", query = "SELECT e FROM EstadoCuenta e WHERE e.cuenta= :cuenta ORDER BY e.fecha DESC")})	
public class EstadoCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldoactual")
    private BigDecimal saldoactual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldoanterior")
    private BigDecimal saldoanterior;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estadocuentaId")
    private List<Movimiento> movimientoList;
    @JoinColumns({
        @JoinColumn(name = "cuenta_numero", referencedColumnName = "numero"),
        @JoinColumn(name = "entidad_id", referencedColumnName = "entidad_id")})
    @ManyToOne(optional = false)
    private Cuenta cuenta;

    public EstadoCuenta() {
    }

    public EstadoCuenta(Integer id) {
        this.id = id;
    }

    public EstadoCuenta(Integer id, Date fecha, BigDecimal saldoactual, BigDecimal saldoanterior) {
        this.id = id;
        this.fecha = fecha;
        this.saldoactual = saldoactual;
        this.saldoanterior = saldoanterior;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSaldoactual() {
        return saldoactual;
    }

    public void setSaldoactual(BigDecimal saldoactual) {
        this.saldoactual = saldoactual;
    }

    public BigDecimal getSaldoanterior() {
        return saldoanterior;
    }

    public void setSaldoanterior(BigDecimal saldoanterior) {
        this.saldoanterior = saldoanterior;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    public String getEstado() {
		return estado;
	}
    
    public void setEstado( EstadosEstadoCuenta estado) {
		this.estado = estado.getValue();
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCuenta)) {
            return false;
        }
        EstadoCuenta other = (EstadoCuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.EstadoCuenta[ id=" + id + " ]";
    }
    
}
