/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "musicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Musico.findAll", query = "SELECT m FROM Musico m"),
    @NamedQuery(name = "Musico.findByIdmusico", query = "SELECT m FROM Musico m WHERE m.idmusico = :idmusico"),
    @NamedQuery(name = "Musico.findByNombre", query = "SELECT m FROM Musico m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Musico.findByApellido1", query = "SELECT m FROM Musico m WHERE m.apellido1 = :apellido1"),
    @NamedQuery(name = "Musico.findByApellido2", query = "SELECT m FROM Musico m WHERE m.apellido2 = :apellido2"),
    @NamedQuery(name = "Musico.findByUrlfoto", query = "SELECT m FROM Musico m WHERE m.urlfoto = :urlfoto"),
    @NamedQuery(name = "Musico.findByFechanacimiento", query = "SELECT m FROM Musico m WHERE m.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "Musico.findByFechadefuncion", query = "SELECT m FROM Musico m WHERE m.fechadefuncion = :fechadefuncion")})
public class Musico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmusico")
    private Integer idmusico;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 30)
    @Column(name = "apellido1")
    private String apellido1;
    @Size(max = 30)
    @Column(name = "apellido2")
    private String apellido2;
    @Size(max = 30)
    @Column(name = "urlfoto")
    private String urlfoto;
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Column(name = "fechadefuncion")
    @Temporal(TemporalType.DATE)
    private Date fechadefuncion;
    @ManyToMany(mappedBy = "musicoList")
    private List<Instrumento> instrumentoList;
    @ManyToMany(mappedBy = "musicoList")
    private List<Grupo> grupoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmusico")
    private List<Comentario> comentarioList;

    public Musico() {
    }

    public Musico(Integer idmusico) {
        this.idmusico = idmusico;
    }

    public Integer getIdmusico() {
        return idmusico;
    }

    public void setIdmusico(Integer idmusico) {
        this.idmusico = idmusico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Date getFechadefuncion() {
        return fechadefuncion;
    }

    public void setFechadefuncion(Date fechadefuncion) {
        this.fechadefuncion = fechadefuncion;
    }

    @XmlTransient
    public List<Instrumento> getInstrumentoList() {
        return instrumentoList;
    }

    public void setInstrumentoList(List<Instrumento> instrumentoList) {
        this.instrumentoList = instrumentoList;
    }

    @XmlTransient
    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmusico != null ? idmusico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Musico)) {
            return false;
        }
        Musico other = (Musico) object;
        if ((this.idmusico == null && other.idmusico != null) || (this.idmusico != null && !this.idmusico.equals(other.idmusico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Musico[ idmusico=" + idmusico + " ]";
    }
    
}
