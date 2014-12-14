/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jonathan
 */
@Entity
@Table(name = "Comisiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comision.findAll", query = "SELECT c FROM Comision c"),
    @NamedQuery(name = "Comision.findById", query = "SELECT c FROM Comision c WHERE c.id = :id"),
    @NamedQuery(name = "Comision.findByValor", query = "SELECT c FROM Comision c WHERE c.valor = :valor"),
    @NamedQuery(name = "Comision.findByDetalle", query = "SELECT c FROM Comision c WHERE c.detalle = :detalle")})
public class Comision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Size(max = 45)
    @Column(name = "detalle")
    private String detalle;
    @JoinColumn(name = "comercio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Comercio comercioId;
    @JoinColumn(name = "tipocomision_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoComision tipocomisionId;
    @JoinColumn(name = "cierre_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cierre cierreId;

    public Comision() {
    }

    public Comision(Long id) {
        this.id = id;
    }

    public Comision(Long id, BigDecimal valor) {
        this.id = id;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Comercio getComercioId() {
        return comercioId;
    }

    public void setComercioId(Comercio comercioId) {
        this.comercioId = comercioId;
    }

    public TipoComision getTipocomisionId() {
        return tipocomisionId;
    }

    public void setTipocomisionId(TipoComision tipocomisionId) {
        this.tipocomisionId = tipocomisionId;
    }

    public Cierre getCierreId() {
        return cierreId;
    }

    public void setCierreId(Cierre cierreId) {
        this.cierreId = cierreId;
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
        if (!(object instanceof Comision)) {
            return false;
        }
        Comision other = (Comision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.Comision[ id=" + id + " ]";
    }
    
}
