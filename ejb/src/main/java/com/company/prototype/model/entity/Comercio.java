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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

/**
 *
 * @author jonathan
 */
@Entity
@Table(name = "Comercios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comercio.findAll", query = "SELECT c FROM Comercio c"),
    @NamedQuery(name = "Comercio.findById", query = "SELECT c FROM Comercio c WHERE c.id = :id"),
    @NamedQuery(name = "Comercio.findByIdentificacion", query = "SELECT c FROM Comercio c WHERE c.identificacion = :identificacion"),
    @NamedQuery(name = "Comercio.findByReferencia", query = "SELECT c FROM Comercio c WHERE c.referencia = :referencia"),
    @NamedQuery(name = "Comercio.findByNombre", query = "SELECT c FROM Comercio c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Comercio.findByEstado", query = "SELECT c FROM Comercio c WHERE c.estado = :estado"),
    @NamedQuery(name = "Comercio.findByPais", query = "SELECT c FROM Comercio c WHERE c.pais = :pais"),
    @NamedQuery(name = "Comercio.findByCiudad", query = "SELECT c FROM Comercio c WHERE c.ciudad = :ciudad"),
    @NamedQuery(name = "Comercio.findByDireccion", query = "SELECT c FROM Comercio c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Comercio.findByContactos", query = "SELECT c FROM Comercio c WHERE c.contactos = :contactos"),
    @NamedQuery(name = "Comercio.findByAdmCreacion", query = "SELECT c FROM Comercio c WHERE c.admCreacion = :admCreacion"),
    @NamedQuery(name = "Comercio.findByAdmActualizacion", query = "SELECT c FROM Comercio c WHERE c.admActualizacion = :admActualizacion"),
    @NamedQuery(name = "Comercio.findByAdmUsuario", query = "SELECT c FROM Comercio c WHERE c.admUsuario = :admUsuario")})
public class Comercio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "identificacion")
    private String identificacion;
    @Size(max = 45)
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
    private String estado;
    @Size(max = 45)
    @Column(name = "pais")
    private String pais;
    @Size(max = 45)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comercioId")
    private List<Comision> comisionList;
    @OneToMany(mappedBy = "comercioId")
    private List<Transaccion> transaccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comercioId")
    private List<TipoComision> tipoComisionList;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente clienteId;

    public Comercio() {
    }

    public Comercio(Long id) {
        this.id = id;
    }

    public Comercio(Long id, String identificacion, String nombre, String estado, Date admCreacion, String admUsuario) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.estado = estado;
        this.admCreacion = admCreacion;
        this.admUsuario = admUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
    public List<Comision> getComisionList() {
        return comisionList;
    }

    public void setComisionList(List<Comision> comisionList) {
        this.comisionList = comisionList;
    }

    @XmlTransient
    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    @XmlTransient
    public List<TipoComision> getTipoComisionList() {
        return tipoComisionList;
    }

    public void setTipoComisionList(List<TipoComision> tipoComisionList) {
        this.tipoComisionList = tipoComisionList;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
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
        if (!(object instanceof Comercio)) {
            return false;
        }
        Comercio other = (Comercio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.Comercio[ id=" + id + " ]";
    }
    
}
