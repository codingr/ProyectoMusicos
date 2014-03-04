/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "equipamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipamiento.findAll", query = "SELECT e FROM Equipamiento e"),
    @NamedQuery(name = "Equipamiento.findByIdMusico", query = "SELECT e FROM Equipamiento e WHERE e.equipamientoPK.idMusico = :idMusico"),
    @NamedQuery(name = "Equipamiento.findByIdInstrumento", query = "SELECT e FROM Equipamiento e WHERE e.equipamientoPK.idInstrumento = :idInstrumento")})
public class Equipamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EquipamientoPK equipamientoPK;

    public Equipamiento() {
    }

    public Equipamiento(EquipamientoPK equipamientoPK) {
        this.equipamientoPK = equipamientoPK;
    }

    public Equipamiento(int idMusico, int idInstrumento) {
        this.equipamientoPK = new EquipamientoPK(idMusico, idInstrumento);
    }

    public EquipamientoPK getEquipamientoPK() {
        return equipamientoPK;
    }

    public void setEquipamientoPK(EquipamientoPK equipamientoPK) {
        this.equipamientoPK = equipamientoPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipamientoPK != null ? equipamientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipamiento)) {
            return false;
        }
        Equipamiento other = (Equipamiento) object;
        if ((this.equipamientoPK == null && other.equipamientoPK != null) || (this.equipamientoPK != null && !this.equipamientoPK.equals(other.equipamientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Equipamiento[ equipamientoPK=" + equipamientoPK + " ]";
    }
    
}
