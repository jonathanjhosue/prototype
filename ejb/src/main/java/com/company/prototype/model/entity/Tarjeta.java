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
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 *
 * @author jonathan
 */
@Entity
@Table(name = "Tarjetas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarjeta.findAll", query = "SELECT t FROM Tarjeta t"),
    @NamedQuery(name = "Tarjeta.findByNumero", query = "SELECT t FROM Tarjeta t WHERE t.numero = :numero"),
    @NamedQuery(name = "Tarjeta.findByUltimos4", query = "SELECT t FROM Tarjeta t WHERE t.ultimos4 = :ultimos4"),
    @NamedQuery(name = "Tarjeta.findByTipoTarjeta", query = "SELECT t FROM Tarjeta t WHERE t.tipoTarjeta = :tipoTarjeta"),
    @NamedQuery(name = "Tarjeta.findByMarca", query = "SELECT t FROM Tarjeta t WHERE t.marca = :marca"),
    @NamedQuery(name = "Tarjeta.findByAdmCreacion", query = "SELECT t FROM Tarjeta t WHERE t.admCreacion = :admCreacion"),
    @NamedQuery(name = "Tarjeta.findByAdmActualizacion", query = "SELECT t FROM Tarjeta t WHERE t.admActualizacion = :admActualizacion"),
    @NamedQuery(name = "Tarjeta.findByAdmUsuario", query = "SELECT t FROM Tarjeta t WHERE t.admUsuario = :admUsuario")})
public class Tarjeta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "numero")
    private String numero;
    @Size(max = 4)
    @Column(name = "ultimos4")
    private String ultimos4;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_tarjeta")
    private String tipoTarjeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "marca")
    private String marca;
    
    @NotNull
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    
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
    @OneToMany(mappedBy = "tarjetaNumero")
    private List<Transaccion> transaccionList;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente clienteId;
    @JoinColumns({
        @JoinColumn(name = "cuenta_numero", referencedColumnName = "numero"),
        @JoinColumn(name = "entidad_id", referencedColumnName = "entidad_id")})
    @ManyToOne(optional = false)
    private Cuenta cuenta;
    /*@JoinColumn(name = "entidad_id", referencedColumnName = "id", updatable=false,insertable=false)
    @ManyToOne(optional = false)
    private Entidad entidadId;*/

    public Tarjeta() {
    }

    public Tarjeta(String numero) {
        this.numero = numero;
    }

    public Tarjeta(String numero, String tipoTarjeta, String marca, Date admCreacion, String admUsuario) {
        this.numero = numero;
        this.tipoTarjeta = tipoTarjeta;
        this.marca = marca;
        this.admCreacion = admCreacion;
        this.admUsuario = admUsuario;
    }
    
    
    public Date getFechaVencimiento() {
  		return fechaVencimiento;
  	}

  	public void setFechaVencimiento(Date fechaVencimiento) {
  		this.fechaVencimiento = fechaVencimiento;
  	}

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUltimos4() {
        return ultimos4;
    }

    public void setUltimos4(String ultimos4) {
        this.ultimos4 = ultimos4;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    /*
    public Entidad getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(Entidad entidadId) {
        this.entidadId = entidadId;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarjeta)) {
            return false;
        }
        Tarjeta other = (Tarjeta) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.Tarjeta_1[ numero=" + numero + " ]";
    }
    
}
