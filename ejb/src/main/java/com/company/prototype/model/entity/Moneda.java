/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

/**
 *
 * @author jonathan
 */
@Entity
@Table(name = "Monedas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moneda.findAll", query = "SELECT m FROM Moneda m"),
    @NamedQuery(name = "Moneda.findById", query = "SELECT m FROM Moneda m WHERE m.id = :id"),
    @NamedQuery(name = "Moneda.findByNombre", query = "SELECT m FROM Moneda m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Moneda.findBySimbolo", query = "SELECT m FROM Moneda m WHERE m.simbolo = :simbolo"),
    @NamedQuery(name = "Moneda.findByAdmCreacion", query = "SELECT m FROM Moneda m WHERE m.admCreacion = :admCreacion"),
    @NamedQuery(name = "Moneda.findByAdmActualizacion", query = "SELECT m FROM Moneda m WHERE m.admActualizacion = :admActualizacion"),
    @NamedQuery(name = "Moneda.findByAdmUsuario", query = "SELECT m FROM Moneda m WHERE m.admUsuario = :admUsuario")})
public class Moneda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "simbolo")
    private Character simbolo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adm_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date admCreacion;
    @Column(name = "adm_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date admActualizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "adm_usuario")
    private String admUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monedaId")
    private List<Cuenta> cuentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monedaId")
    private List<Transaccion> transaccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moneda2")
    private List<TipoCambio> tipoCambioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moneda1")
    private List<TipoCambio> tipoCambioList1;

    public Moneda() {
    }

    public Moneda(String id) {
        this.id = id;
    }

    public Moneda(String id, String nombre, Character simbolo, Date admCreacion, String admUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.admCreacion = admCreacion;
        this.admUsuario = admUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Character simbolo) {
        this.simbolo = simbolo;
    }

    public Date getAdmCreacion() {
        return admCreacion;
    }

    public void setAdmCreacion(Date admCreacion) {
        this.admCreacion = admCreacion;
    }

    public Date getAdmActualizacion() {
        return admActualizacion;
    }

    public void setAdmActualizacion(Date admActualizacion) {
        this.admActualizacion = admActualizacion;
    }

    public String getAdmUsuario() {
        return admUsuario;
    }

    public void setAdmUsuario(String admUsuario) {
        this.admUsuario = admUsuario;
    }

    @XmlTransient
    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    @XmlTransient
    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    @XmlTransient
    public List<TipoCambio> getTipoCambioList() {
        return tipoCambioList;
    }

    public void setTipoCambioList(List<TipoCambio> tipoCambioList) {
        this.tipoCambioList = tipoCambioList;
    }

    @XmlTransient
    public List<TipoCambio> getTipoCambioList1() {
        return tipoCambioList1;
    }

    public void setTipoCambioList1(List<TipoCambio> tipoCambioList1) {
        this.tipoCambioList1 = tipoCambioList1;
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
        if (!(object instanceof Moneda)) {
            return false;
        }
        Moneda other = (Moneda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.Moneda[ id=" + id + " ]";
    }
    
}
