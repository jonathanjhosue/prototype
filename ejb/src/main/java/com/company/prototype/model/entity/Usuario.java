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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "Usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuarioPK.usuario = :usuario"),
    @NamedQuery(name = "Usuario.findByReferencia", query = "SELECT u FROM Usuario u WHERE u.usuarioPK.referencia = :referencia"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuario.findByRol", query = "SELECT u FROM Usuario u WHERE u.rol = :rol"),
    @NamedQuery(name = "Usuario.findByAdmCreacion", query = "SELECT u FROM Usuario u WHERE u.admCreacion = :admCreacion"),
    @NamedQuery(name = "Usuario.findByAdmActualizacion", query = "SELECT u FROM Usuario u WHERE u.admActualizacion = :admActualizacion"),
    @NamedQuery(name = "Usuario.findByAdmUsuario", query = "SELECT u FROM Usuario u WHERE u.admUsuario = :admUsuario")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioPK usuarioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rol")
    private String rol;
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

    public Usuario() {
    }

    public Usuario(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public Usuario(UsuarioPK usuarioPK, String password, String estado, String rol, Date admCreacion, String admUsuario) {
        this.usuarioPK = usuarioPK;
        this.password = password;
        this.estado = estado;
        this.rol = rol;
        this.admCreacion = admCreacion;
        this.admUsuario = admUsuario;
    }

    public Usuario(String usuario, String referencia) {
        this.usuarioPK = new UsuarioPK(usuario, referencia);
    }

    public UsuarioPK getUsuarioPK() {
        return usuarioPK;
    }

    public void setUsuarioPK(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioPK != null ? usuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuarioPK == null && other.usuarioPK != null) || (this.usuarioPK != null && !this.usuarioPK.equals(other.usuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.prototype.model.entity.Usuario[ usuarioPK=" + usuarioPK + " ]";
    }
    
}
