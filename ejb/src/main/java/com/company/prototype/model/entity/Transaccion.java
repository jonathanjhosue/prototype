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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

import com.company.prototype.util.ApplicationConfiguration.EstadosTransaccion;
import com.company.prototype.util.ApplicationConfiguration.TiposTransaccion;

/**
 *
 * @author jonathan
 */
@Entity
@Table(name = "Transacciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaccion.findAll", query = "SELECT t FROM Transaccion t"),
    @NamedQuery(name = "Transaccion.findById", query = "SELECT t FROM Transaccion t WHERE t.id = :id"),
    @NamedQuery(name = "Transaccion.findByIdentificacion", query = "SELECT t FROM Transaccion t WHERE t.identificacion = :identificacion"),
    @NamedQuery(name = "Transaccion.findByReferencia", query = "SELECT t FROM Transaccion t WHERE t.referencia = :referencia"),
    @NamedQuery(name = "Transaccion.findByFecha", query = "SELECT t FROM Transaccion t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Transaccion.findByEstado", query = "SELECT t FROM Transaccion t WHERE t.estado = :estado"),
    @NamedQuery(name = "Transaccion.findByValor", query = "SELECT t FROM Transaccion t WHERE t.valor = :valor"),
	@NamedQuery(name = "Transaccion.findByComercio", query = "SELECT t FROM Transaccion t WHERE t.comercioId = :comercioId")})
public class Transaccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue (strategy=GenerationType.AUTO)
    private Long id;
    @Size(max = 45)
    @Column(name = "identificacion")
    private String identificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaccionId")
    private List<Movimiento> movimientoList;
    @JoinColumn(name = "comercio_id", referencedColumnName = "id")
    @ManyToOne
    private Comercio comercioId;
    @JoinColumns({
        @JoinColumn(name = "cuentaorigen_numero", referencedColumnName = "numero"),
        @JoinColumn(name = "entidadorigen_id", referencedColumnName = "entidad_id")})
    @ManyToOne
    private Cuenta cuenta;
    @JoinColumns({
        @JoinColumn(name = "cuentadestino_numero", referencedColumnName = "numero"),
        @JoinColumn(name = "entidaddestino_id", referencedColumnName = "entidad_id")})
    @ManyToOne
    private Cuenta cuenta1;
    @JoinColumn(name = "moneda_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Moneda monedaId;
    @JoinColumn(name = "tarjeta_numero", referencedColumnName = "numero")
    @ManyToOne
    private Tarjeta tarjetaNumero;
    @JoinColumn(name = "tipocambio_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private TipoCambio tipocambioId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipotransaccion")
    private String tipotransaccion;

    public Transaccion() {
    }

    public Transaccion(Long id) {
        this.id = id;
    }

    public Transaccion(Long id, String referencia, Date fecha, String estado, BigDecimal valor) {
        this.id = id;
        this.referencia = referencia;
        this.fecha = fecha;
        this.estado = estado;
        this.valor = valor;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }
    
    public void setEstado(EstadosTransaccion estado) {
        this.estado = estado.getValue();
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    public Comercio getComercioId() {
        return comercioId;
    }

    public void setComercioId(Comercio comercioId) {
        this.comercioId = comercioId;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    /*
     * Cuenta From
     * 
     * */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    /*
     * Cuenta To
     * **/
    public Cuenta getCuenta1() {
        return cuenta1;
    }

    public void setCuenta1(Cuenta cuenta1) {
        this.cuenta1 = cuenta1;
    }

    public Moneda getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(Moneda monedaId) {
        this.monedaId = monedaId;
    }

    public Tarjeta getTarjetaNumero() {
        return tarjetaNumero;
    }

    public void setTarjetaNumero(Tarjeta tarjetaNumero) {
        this.tarjetaNumero = tarjetaNumero;
    }

    public TipoCambio getTipocambioId() {
        return tipocambioId;
    }

    public void setTipocambioId(TipoCambio tipocambioId) {
        this.tipocambioId = tipocambioId;
    }

    public String getTipotransaccion() {
        return tipotransaccion;
    }
    
    public void setTipotransaccion(TiposTransaccion tipoTransaccion) {
        this.tipotransaccion = tipoTransaccion.getValue();
    }

    public void setTipotransaccion(String tipotransaccion) {
        this.tipotransaccion = tipotransaccion;
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
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.Transaccion[ id=" + id + " ]";
    }
    
}
