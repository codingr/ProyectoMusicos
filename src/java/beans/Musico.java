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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "musicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Musico.findAll", query = "SELECT m FROM Musico m"),
    @NamedQuery(name = "Musico.findByIdE", query = "SELECT m FROM Musico m WHERE m.idE = :idE"),
    @NamedQuery(name = "Musico.findByNombre", query = "SELECT m FROM Musico m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Musico.findByApellido", query = "SELECT m FROM Musico m WHERE m.apellido = :apellido"),
    @NamedQuery(name = "Musico.findByAlias", query = "SELECT m FROM Musico m WHERE m.alias = :alias"),
    @NamedQuery(name = "Musico.findByUrlfoto", query = "SELECT m FROM Musico m WHERE m.urlfoto = :urlfoto"),
    @NamedQuery(name = "Musico.findByFechanacimiento", query = "SELECT m FROM Musico m WHERE m.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "Musico.findByFechadefuncion", query = "SELECT m FROM Musico m WHERE m.fechadefuncion = :fechadefuncion")})
public class Musico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idE")
    private Integer idE;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 30)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 30)
    @Column(name = "alias")
    private String alias;
    @Size(max = 30)
    @Column(name = "urlfoto")
    private String urlfoto;
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Column(name = "fechadefuncion")
    @Temporal(TemporalType.DATE)
    private Date fechadefuncion;
    @JoinTable(name = "equipamiento", joinColumns = {
        @JoinColumn(name = "idMusico", referencedColumnName = "idE")}, inverseJoinColumns = {
        @JoinColumn(name = "idInstrumento", referencedColumnName = "idE")})
    @ManyToMany
    private List<Instrumento> instrumentoList;
    @JoinTable(name = "miembros", joinColumns = {
        @JoinColumn(name = "idE", referencedColumnName = "idE")}, inverseJoinColumns = {
        @JoinColumn(name = "idGrupo", referencedColumnName = "idgrupo")})
    @ManyToMany
    private List<Grupo> grupoList;

    public Musico() {
    }

    public Musico(Integer idE) {
        this.idE = idE;
    }

    public Integer getIdE() {
        return idE;
    }

    public void setIdE(Integer idE) {
        this.idE = idE;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idE != null ? idE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Musico)) {
            return false;
        }
        Musico other = (Musico) object;
        if ((this.idE == null && other.idE != null) || (this.idE != null && !this.idE.equals(other.idE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Musico[ idE=" + idE + " ]";
    }
    
}
