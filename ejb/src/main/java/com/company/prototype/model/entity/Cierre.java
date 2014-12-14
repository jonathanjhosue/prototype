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
@Table(name = "Cierres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cierre.findAll", query = "SELECT c FROM Cierre c"),
    @NamedQuery(name = "Cierre.findById", query = "SELECT c FROM Cierre c WHERE c.id = :id"),
    @NamedQuery(name = "Cierre.findByFecha", query = "SELECT c FROM Cierre c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Cierre.findByAdmUsuario", query = "SELECT c FROM Cierre c WHERE c.admUsuario = :admUsuario")})
public class Cierre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 45)
    @Column(name = "adm_usuario")
    private String admUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cierreId")
    private List<Comision> comisionList;
    @JoinColumn(name = "entidad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Entidad entidadId;

    public Cierre() {
    }

    public Cierre(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public Entidad getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(Entidad entidadId) {
        this.entidadId = entidadId;
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
        if (!(object instanceof Cierre)) {
            return false;
        }
        Cierre other = (Cierre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.Cierre[ id=" + id + " ]";
    }
    
}
