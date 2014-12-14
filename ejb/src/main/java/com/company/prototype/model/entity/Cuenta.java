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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "Cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findByNumero", query = "SELECT c FROM Cuenta c WHERE c.cuentaPK.numero = :numero"),
    @NamedQuery(name = "Cuenta.findByEntidadId", query = "SELECT c FROM Cuenta c WHERE c.cuentaPK.entidadId = :entidadId"),
    @NamedQuery(name = "Cuenta.findByEstado", query = "SELECT c FROM Cuenta c WHERE c.estado = :estado"),
    @NamedQuery(name = "Cuenta.findByTipoCuenta", query = "SELECT c FROM Cuenta c WHERE c.tipoCuenta = :tipoCuenta"),
    @NamedQuery(name = "Cuenta.findByFechaCreacion", query = "SELECT c FROM Cuenta c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Cuenta.findByFechaActivacion", query = "SELECT c FROM Cuenta c WHERE c.fechaActivacion = :fechaActivacion"),
    @NamedQuery(name = "Cuenta.findByFechaDesactivacion", query = "SELECT c FROM Cuenta c WHERE c.fechaDesactivacion = :fechaDesactivacion"),
    @NamedQuery(name = "Cuenta.findByPredeterminada", query = "SELECT c FROM Cuenta c WHERE c.predeterminada = :predeterminada"),
    @NamedQuery(name = "Cuenta.findByAdmCreacion", query = "SELECT c FROM Cuenta c WHERE c.admCreacion = :admCreacion"),
    @NamedQuery(name = "Cuenta.findByAdmActualizacion", query = "SELECT c FROM Cuenta c WHERE c.admActualizacion = :admActualizacion"),
    @NamedQuery(name = "Cuenta.findByAdmUsuario", query = "SELECT c FROM Cuenta c WHERE c.admUsuario = :admUsuario"),
	@NamedQuery(name = "Cuenta.findByTarjeta", query = "SELECT c FROM Tarjeta t INNER JOIN t.cuenta c WHERE t.numero = :numeroTarjeta")})
public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CuentaPK cuentaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_activacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActivacion;
    @Column(name = "fecha_desactivacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesactivacion;
    @Column(name = "predeterminada")
    private Boolean predeterminada;
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
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente clienteId;
    @JoinColumn(name = "entidad_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Entidad entidad;
    @JoinColumn(name = "moneda_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Moneda monedaId;
    @OneToMany(mappedBy = "cuenta")
    private List<Transaccion> transaccionList;
    @OneToMany(mappedBy = "cuenta1")
    private List<Transaccion> transaccionList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private List<Tarjeta> tarjetaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
    private List<EstadoCuenta> estadoCuentaList; 
    

    public Cuenta() {
    }

    public Cuenta(CuentaPK cuentaPK) {
        this.cuentaPK = cuentaPK;
    }

    public Cuenta(CuentaPK cuentaPK, String estado, String tipoCuenta, Date fechaCreacion, Date admCreacion, String admUsuario) {
        this.cuentaPK = cuentaPK;
        this.estado = estado;
        this.tipoCuenta = tipoCuenta;
        this.fechaCreacion = fechaCreacion;
        this.admCreacion = admCreacion;
        this.admUsuario = admUsuario;
    }

    public Cuenta(String numero, String entidadId) {
        this.cuentaPK = new CuentaPK(numero, entidadId);
    }

    public CuentaPK getCuentaPK() {
        return cuentaPK;
    }

    public void setCuentaPK(CuentaPK cuentaPK) {
        this.cuentaPK = cuentaPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public Date getFechaDesactivacion() {
        return fechaDesactivacion;
    }

    public void setFechaDesactivacion(Date fechaDesactivacion) {
        this.fechaDesactivacion = fechaDesactivacion;
    }

    public Boolean getPredeterminada() {
        return predeterminada;
    }

    public void setPredeterminada(Boolean predeterminada) {
        this.predeterminada = predeterminada;
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

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public Moneda getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(Moneda monedaId) {
        this.monedaId = monedaId;
    }

    @XmlTransient
    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    @XmlTransient
    public List<Transaccion> getTransaccionList1() {
        return transaccionList1;
    }

    public void setTransaccionList1(List<Transaccion> transaccionList1) {
        this.transaccionList1 = transaccionList1;
    }

    @XmlTransient
    public List<Tarjeta> getTarjetaList() {
        return tarjetaList;
    }

    public void setTarjetaList(List<Tarjeta> tarjetaList) {
        this.tarjetaList = tarjetaList;
    }

    @XmlTransient
    public List<EstadoCuenta> getEstadoCuentaList() {
        return estadoCuentaList;
    }

    public void setEstadoCuentaList(List<EstadoCuenta> estadoCuentaList) {
        this.estadoCuentaList = estadoCuentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaPK != null ? cuentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.cuentaPK == null && other.cuentaPK != null) || (this.cuentaPK != null && !this.cuentaPK.equals(other.cuentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.Cuenta[ cuentaPK=" + cuentaPK + " ]";
    }
    
}
