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
import javax.persistence.Cacheable;
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
@Cacheable(true)//pocas veces modificadas, se puede cachear en el L2 de JPA para wildfly ->infinispan
@Entity
@Table(name = "Entidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entidad.findAll", query = "SELECT e FROM Entidad e"),
    @NamedQuery(name = "Entidad.findById", query = "SELECT e FROM Entidad e WHERE e.id = :id"),
    @NamedQuery(name = "Entidad.findByReferencia", query = "SELECT e FROM Entidad e WHERE e.referencia = :referencia"),
    @NamedQuery(name = "Entidad.findByNombre", query = "SELECT e FROM Entidad e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Entidad.findByEstado", query = "SELECT e FROM Entidad e WHERE e.estado = :estado"),
    @NamedQuery(name = "Entidad.findByPais", query = "SELECT e FROM Entidad e WHERE e.pais = :pais"),
    @NamedQuery(name = "Entidad.findByUrl", query = "SELECT e FROM Entidad e WHERE e.url = :url"),
    @NamedQuery(name = "Entidad.findByContactos", query = "SELECT e FROM Entidad e WHERE e.contactos = :contactos"),
    @NamedQuery(name = "Entidad.findByAdmCreacion", query = "SELECT e FROM Entidad e WHERE e.admCreacion = :admCreacion"),
    @NamedQuery(name = "Entidad.findByAdmActualizacion", query = "SELECT e FROM Entidad e WHERE e.admActualizacion = :admActualizacion"),
    @NamedQuery(name = "Entidad.findByAdmUsuario", query = "SELECT e FROM Entidad e WHERE e.admUsuario = :admUsuario")})
public class Entidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private Character estado;
    @Size(max = 45)
    @Column(name = "pais")
    private String pais;
    @Size(max = 255)
    @Column(name = "url")
    private String url;
    @Size(max = 510)
    @Column(name = "contactos")
    private String contactos;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidad")
    private List<Cuenta> cuentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidadId")
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidadId")
    private List<Tarjeta> tarjetaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidadId")
    private List<Cierre> cierreList;

    public Entidad() {
    }

    public Entidad(String id) {
        this.id = id;
    }

    public Entidad(String id, String referencia, String nombre, Character estado, Date admCreacion, String admUsuario) {
        this.id = id;
        this.referencia = referencia;
        this.nombre = nombre;
        this.estado = estado;
        this.admCreacion = admCreacion;
        this.admUsuario = admUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContactos() {
        return contactos;
    }

    public void setContactos(String contactos) {
        this.contactos = contactos;
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
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @XmlTransient
    public List<Tarjeta> getTarjetaList() {
        return tarjetaList;
    }

    public void setTarjetaList(List<Tarjeta> tarjetaList) {
        this.tarjetaList = tarjetaList;
    }

    @XmlTransient
    public List<Cierre> getCierreList() {
        return cierreList;
    }

    public void setCierreList(List<Cierre> cierreList) {
        this.cierreList = cierreList;
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
        if (!(object instanceof Entidad)) {
            return false;
        }
        Entidad other = (Entidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.Entidad[ id=" + id + " ]";
    }
    
}
