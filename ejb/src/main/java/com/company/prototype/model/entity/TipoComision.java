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
@Table(name = "TipoComisiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoComision.findAll", query = "SELECT t FROM TipoComision t"),
    @NamedQuery(name = "TipoComision.findById", query = "SELECT t FROM TipoComision t WHERE t.id = :id"),
    @NamedQuery(name = "TipoComision.findByFecha", query = "SELECT t FROM TipoComision t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "TipoComision.findByTipoComision", query = "SELECT t FROM TipoComision t WHERE t.tipoComision = :tipoComision"),
    @NamedQuery(name = "TipoComision.findByPorcentaje", query = "SELECT t FROM TipoComision t WHERE t.porcentaje = :porcentaje"),
    @NamedQuery(name = "TipoComision.findByEstado", query = "SELECT t FROM TipoComision t WHERE t.estado = :estado"),
    @NamedQuery(name = "TipoComision.findByAdmCreacion", query = "SELECT t FROM TipoComision t WHERE t.admCreacion = :admCreacion"),
    @NamedQuery(name = "TipoComision.findByAdmActualizacion", query = "SELECT t FROM TipoComision t WHERE t.admActualizacion = :admActualizacion"),
    @NamedQuery(name = "TipoComision.findByAdmUsuario", query = "SELECT t FROM TipoComision t WHERE t.admUsuario = :admUsuario")})
public class TipoComision implements Serializable {
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
    @Size(max = 45)
    @Column(name = "tipo_comision")
    private String tipoComision;
    @Column(name = "porcentaje")
    private Long porcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipocomisionId")
    private List<Comision> comisionList;
    @JoinColumn(name = "comercio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Comercio comercioId;

    public TipoComision() {
    }

    public TipoComision(Integer id) {
        this.id = id;
    }

    public TipoComision(Integer id, Date fecha, String estado, Date admCreacion, String admUsuario) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.admCreacion = admCreacion;
        this.admUsuario = admUsuario;
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

    public String getTipoComision() {
        return tipoComision;
    }

    public void setTipoComision(String tipoComision) {
        this.tipoComision = tipoComision;
    }

    public Long getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Long porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Comercio getComercioId() {
        return comercioId;
    }

    public void setComercioId(Comercio comercioId) {
        this.comercioId = comercioId;
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
        if (!(object instanceof TipoComision)) {
            return false;
        }
        TipoComision other = (TipoComision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.TipoComision[ id=" + id + " ]";
    }
    
}
