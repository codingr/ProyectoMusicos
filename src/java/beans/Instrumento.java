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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @NamedQuery(name = "Instrumento.findByIdinstrumento", query = "SELECT i FROM Instrumento i WHERE i.idinstrumento = :idinstrumento"),
    @NamedQuery(name = "Instrumento.findByUrlfoto", query = "SELECT i FROM Instrumento i WHERE i.urlfoto = :urlfoto"),
    @NamedQuery(name = "Instrumento.findByTipo", query = "SELECT i FROM Instrumento i WHERE i.tipo = :tipo")})
public class Instrumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinstrumento")
    private Integer idinstrumento;
    @Size(max = 30)
    @Column(name = "urlfoto")
    private String urlfoto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipo")
    private String tipo;
    @JoinTable(name = "detalles", joinColumns = {
        @JoinColumn(name = "idinstrumento", referencedColumnName = "idinstrumento")}, inverseJoinColumns = {
        @JoinColumn(name = "idmusico", referencedColumnName = "idmusico")})
    @ManyToMany
    private List<Musico> musicoList;

    public Instrumento() {
    }

    public Instrumento(Integer idinstrumento) {
        this.idinstrumento = idinstrumento;
    }

    public Instrumento(Integer idinstrumento, String tipo) {
        this.idinstrumento = idinstrumento;
        this.tipo = tipo;
    }

    public Integer getIdinstrumento() {
        return idinstrumento;
    }

    public void setIdinstrumento(Integer idinstrumento) {
        this.idinstrumento = idinstrumento;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Musico> getMusicoList() {
        return musicoList;
    }

    public void setMusicoList(List<Musico> musicoList) {
        this.musicoList = musicoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinstrumento != null ? idinstrumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instrumento)) {
            return false;
        }
        Instrumento other = (Instrumento) object;
        if ((this.idinstrumento == null && other.idinstrumento != null) || (this.idinstrumento != null && !this.idinstrumento.equals(other.idinstrumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Instrumento[ idinstrumento=" + idinstrumento + " ]";
    }
    
}
