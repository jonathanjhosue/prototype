/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jonathan
 */
@Embeddable
public class TipoCambioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "monedaorigen_id")
    private String monedaorigenId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "monedadestino_id")
    private String monedadestinoId;

    public TipoCambioPK() {
    }

    public TipoCambioPK(Date fecha, String monedaorigenId, String monedadestinoId) {
        this.fecha = fecha;
        this.monedaorigenId = monedaorigenId;
        this.monedadestinoId = monedadestinoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMonedaorigenId() {
        return monedaorigenId;
    }

    public void setMonedaorigenId(String monedaorigenId) {
        this.monedaorigenId = monedaorigenId;
    }

    public String getMonedadestinoId() {
        return monedadestinoId;
    }

    public void setMonedadestinoId(String monedadestinoId) {
        this.monedadestinoId = monedadestinoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (monedaorigenId != null ? monedaorigenId.hashCode() : 0);
        hash += (monedadestinoId != null ? monedadestinoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCambioPK)) {
            return false;
        }
        TipoCambioPK other = (TipoCambioPK) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.monedaorigenId == null && other.monedaorigenId != null) || (this.monedaorigenId != null && !this.monedaorigenId.equals(other.monedaorigenId))) {
            return false;
        }
        if ((this.monedadestinoId == null && other.monedadestinoId != null) || (this.monedadestinoId != null && !this.monedadestinoId.equals(other.monedadestinoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.TipoCambioPK[ fecha=" + fecha + ", monedaorigenId=" + monedaorigenId + ", monedadestinoId=" + monedadestinoId + " ]";
    }
    
}
