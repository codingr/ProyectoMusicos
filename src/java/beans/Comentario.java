/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "comentarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c"),
    @NamedQuery(name = "Comentario.findByIdcomentario", query = "SELECT c FROM Comentario c WHERE c.idcomentario = :idcomentario"),
    @NamedQuery(name = "Comentario.findByMensaje", query = "SELECT c FROM Comentario c WHERE c.mensaje = :mensaje"),
    @NamedQuery(name = "Comentario.findByGusta", query = "SELECT c FROM Comentario c WHERE c.gusta = :gusta"),
    @NamedQuery(name = "Comentario.findByNogusta", query = "SELECT c FROM Comentario c WHERE c.nogusta = :nogusta")})
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomentario")
    private Integer idcomentario;
    @Size(max = 200)
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "gusta")
    private Integer gusta;
    @Column(name = "nogusta")
    private Integer nogusta;
    @JoinColumn(name = "idE", referencedColumnName = "idE")
    @ManyToOne(optional = false)
    private Elemento idE;

    public Comentario() {
    }

    public Comentario(Integer idcomentario) {
        this.idcomentario = idcomentario;
    }

    public Integer getIdcomentario() {
        return idcomentario;
    }

    public void setIdcomentario(Integer idcomentario) {
        this.idcomentario = idcomentario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getGusta() {
        return gusta;
    }

    public void setGusta(Integer gusta) {
        this.gusta = gusta;
    }

    public Integer getNogusta() {
        return nogusta;
    }

    public void setNogusta(Integer nogusta) {
        this.nogusta = nogusta;
    }

    public Elemento getIdE() {
        return idE;
    }

    public void setIdE(Elemento idE) {
        this.idE = idE;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomentario != null ? idcomentario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.idcomentario == null && other.idcomentario != null) || (this.idcomentario != null && !this.idcomentario.equals(other.idcomentario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Comentario[ idcomentario=" + idcomentario + " ]";
    }
    
}
