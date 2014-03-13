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
 * @author alumno
 */
@Entity
@Table(name = "caracteristicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caracteristica.findAll", query = "SELECT c FROM Caracteristica c"),
    @NamedQuery(name = "Caracteristica.findByIdCaracteristica", query = "SELECT c FROM Caracteristica c WHERE c.idCaracteristica = :idCaracteristica"),
    @NamedQuery(name = "Caracteristica.findByIdE", query = "SELECT c FROM Caracteristica c WHERE c.idE = :idE"),
    @NamedQuery(name = "Caracteristica.findByTexto", query = "SELECT c FROM Caracteristica c WHERE c.texto = :texto")})
public class Caracteristica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCaracteristica")
    private Integer idCaracteristica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idE")
    private int idE;
    @Size(max = 30)
    @Column(name = "texto")
    private String texto;
    @ManyToMany(mappedBy = "caracteristicaList")
    private List<Instrumento> instrumentoList;

    public Caracteristica() {
    }

    public Caracteristica(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public Caracteristica(Integer idCaracteristica, int idE) {
        this.idCaracteristica = idCaracteristica;
        this.idE = idE;
    }

    public Integer getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @XmlTransient
    public List<Instrumento> getInstrumentoList() {
        return instrumentoList;
    }

    public void setInstrumentoList(List<Instrumento> instrumentoList) {
        this.instrumentoList = instrumentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaracteristica != null ? idCaracteristica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caracteristica)) {
            return false;
        }
        Caracteristica other = (Caracteristica) object;
        if ((this.idCaracteristica == null && other.idCaracteristica != null) || (this.idCaracteristica != null && !this.idCaracteristica.equals(other.idCaracteristica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Caracteristica[ idCaracteristica=" + idCaracteristica + " ]";
    }
    
}
