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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jonathan
 */
@Entity
@Table(name = "Movimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m"),
    @NamedQuery(name = "Movimiento.findById", query = "SELECT m FROM Movimiento m WHERE m.id = :id"),
    @NamedQuery(name = "Movimiento.findByDescripcion", query = "SELECT m FROM Movimiento m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Movimiento.findByTipoMovimiento", query = "SELECT m FROM Movimiento m WHERE m.tipoMovimiento = :tipoMovimiento"),
    @NamedQuery(name = "Movimiento.findByValor", query = "SELECT m FROM Movimiento m WHERE m.valor = :valor")})
public class Movimiento implements Serializable {


	private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2)
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private long valor;
    @JoinColumn(name = "estadocuenta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoCuenta estadocuentaId;
    @JoinColumn(name = "tipocambio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoCambio tipocambioId;
    @JoinColumn(name = "transaccion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Transaccion transaccionId;
    

    public Movimiento() {
    }

    public Movimiento(Long id) {
        this.id = id;
    }

    public Movimiento(Long id, long valor) {
        this.id = id;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public EstadoCuenta getEstadocuentaId() {
        return estadocuentaId;
    }

    public void setEstadocuentaId(EstadoCuenta estadocuentaId) {
        this.estadocuentaId = estadocuentaId;
    }

    public TipoCambio getTipocambioId() {
        return tipocambioId;
    }

    public void setTipocambioId(TipoCambio tipocambioId) {
        this.tipocambioId = tipocambioId;
    }

    public Transaccion getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(Transaccion transaccionId) {
        this.transaccionId = transaccionId;
    }
    
    public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.Movimiento[ id=" + id + " ]";
    }
    
}
