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

import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "TipoCambios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCambio.findAll", query = "SELECT t FROM TipoCambio t"),
    @NamedQuery(name = "TipoCambio.findById", query = "SELECT t FROM TipoCambio t WHERE t.id = :id"),
    @NamedQuery(name = "TipoCambio.findByFecha", query = "SELECT t FROM TipoCambio t WHERE t.tipoCambioPK.fecha = :fecha"),
    @NamedQuery(name = "TipoCambio.findCurrent", query = "SELECT t FROM TipoCambio t "
    													+ "WHERE t.tipoCambioPK.monedaorigenId = :monedaorigenId  "
    													+ "AND t.tipoCambioPK.monedadestinoId = :monedadestinoId "
    													+ "order by t.tipoCambioPK.fecha desc "),
    @NamedQuery(name = "TipoCambio.findByMonedaorigenId", query = "SELECT t FROM TipoCambio t WHERE t.tipoCambioPK.monedaorigenId = :monedaorigenId"),
    @NamedQuery(name = "TipoCambio.findByMonedadestinoId", query = "SELECT t FROM TipoCambio t WHERE t.tipoCambioPK.monedadestinoId = :monedadestinoId"),
    @NamedQuery(name = "TipoCambio.findByValor", query = "SELECT t FROM TipoCambio t WHERE t.valor = :valor"),
    @NamedQuery(name = "TipoCambio.findByAdmCreacion", query = "SELECT t FROM TipoCambio t WHERE t.admCreacion = :admCreacion"),
    @NamedQuery(name = "TipoCambio.findByAdmUsuario", query = "SELECT t FROM TipoCambio t WHERE t.admUsuario = :admUsuario")})
public class TipoCambio implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
     * Este Id es generado de manera manual en el facade.
     * */
    @Basic(optional = false)
    //@GeneratedValue(strategy = GenerationType.)
    @Column(name = "id")
    private Long id;
    
    @EmbeddedId
    protected TipoCambioPK tipoCambioPK;

    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adm_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date admCreacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "adm_usuario")
    private String admUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipocambioId")
    private List<Movimiento> movimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipocambioId")
    private List<Transaccion> transaccionList;
    @JoinColumn(name = "monedadestino_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda2;
    @JoinColumn(name = "monedaorigen_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Moneda moneda1;

    public TipoCambio() {
    }

    public TipoCambio(TipoCambioPK tipoCambioPK) {
        this.tipoCambioPK = tipoCambioPK;
    }

    public TipoCambio(TipoCambioPK tipoCambioPK, Long id, BigDecimal valor, Date admCreacion, String admUsuario) {
        this.tipoCambioPK = tipoCambioPK;
        this.id = id;
        this.valor = valor;
        this.admCreacion = admCreacion;
        this.admUsuario = admUsuario;
    }

    public TipoCambio(Date fecha, String monedaorigenId, String monedadestinoId) {
        this.tipoCambioPK = new TipoCambioPK(fecha, monedaorigenId, monedadestinoId);
    }

    public TipoCambioPK getTipoCambioPK() {
        return tipoCambioPK;
    }

    public void setTipoCambioPK(TipoCambioPK tipoCambioPK) {
        this.tipoCambioPK = tipoCambioPK;
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

    public Date getAdmCreacion() {
        return admCreacion;
    }

    public void setAdmCreacion(Date admCreacion) {
        this.admCreacion = admCreacion;
    }

    public String getAdmUsuario() {
        return admUsuario;
    }

    public void setAdmUsuario(String admUsuario) {
        this.admUsuario = admUsuario;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    @XmlTransient
    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    public Moneda getMoneda2() {
        return moneda2;
    }

    public void setMoneda2(Moneda moneda) {
        this.moneda2 = moneda;
    }

    public Moneda getMoneda1() {
        return moneda1;
    }

    public void setMoneda1(Moneda moneda1) {
        this.moneda1 = moneda1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCambioPK != null ? tipoCambioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCambio)) {
            return false;
        }
        TipoCambio other = (TipoCambio) object;
        if ((this.tipoCambioPK == null && other.tipoCambioPK != null) || (this.tipoCambioPK != null && !this.tipoCambioPK.equals(other.tipoCambioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.TipoCambio[ tipoCambioPK=" + tipoCambioPK + " ]";
    }
    
}
