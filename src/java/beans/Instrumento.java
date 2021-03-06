/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "instrumentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instrumento.findAll", query = "SELECT i FROM Instrumento i"),
    @NamedQuery(name = "Instrumento.findByIdE", query = "SELECT i FROM Instrumento i WHERE i.idE = :idE"),
    @NamedQuery(name = "Instrumento.findByMarca", query = "SELECT i FROM Instrumento i WHERE i.marca = :marca"),
    @NamedQuery(name = "Instrumento.findByModelo", query = "SELECT i FROM Instrumento i WHERE i.modelo = :modelo"),
    @NamedQuery(name = "Instrumento.findByUrlfoto", query = "SELECT i FROM Instrumento i WHERE i.urlfoto = :urlfoto"),
    @NamedQuery(name = "Instrumento.findByAniofabricacion", query = "SELECT i FROM Instrumento i WHERE i.aniofabricacion = :aniofabricacion")})
public class Instrumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idE")
    private Integer idE;
    @Size(max = 30)
    @Column(name = "marca")
    private String marca;
    @Size(max = 30)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 30)
    @Column(name = "urlfoto")
    private String urlfoto;
    @Size(max = 4)
    @Column(name = "aniofabricacion")
    private String aniofabricacion;
    @JoinTable(name = "equipamiento", joinColumns = {
        @JoinColumn(name = "idInstrumento", referencedColumnName = "idE")}, inverseJoinColumns = {
        @JoinColumn(name = "idMusico", referencedColumnName = "idE")})
    @ManyToMany
    private List<Musico> musicoList;
    @ManyToMany(mappedBy = "instrumentoList")
    private List<Caracteristica> caracteristicaList;

    public Instrumento() {
    }

    public Instrumento(Integer idE) {
        this.idE = idE;
    }

    public Integer getIdE() {
        return idE;
    }

    public void setIdE(Integer idE) {
        this.idE = idE;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public String getAniofabricacion() {
        return aniofabricacion;
    }

    public void setAniofabricacion(String aniofabricacion) {
        this.aniofabricacion = aniofabricacion;
    }

    @XmlTransient
    public List<Musico> getMusicoList() {
        return musicoList;
    }

    public void setMusicoList(List<Musico> musicoList) {
        this.musicoList = musicoList;
    }

    @XmlTransient
    public List<Caracteristica> getCaracteristicaList() {
        return caracteristicaList;
    }

    public void setCaracteristicaList(List<Caracteristica> caracteristicaList) {
        this.caracteristicaList = caracteristicaList;
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
        if (!(object instanceof Instrumento)) {
            return false;
        }
        Instrumento other = (Instrumento) object;
        if ((this.idE == null && other.idE != null) || (this.idE != null && !this.idE.equals(other.idE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Instrumento[ idE=" + idE + " ]";
    }
    
}
