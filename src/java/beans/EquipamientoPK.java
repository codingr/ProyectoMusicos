/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alumno
 */
@Embeddable
public class EquipamientoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMusico")
    private int idMusico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idInstrumento")
    private int idInstrumento;

    public EquipamientoPK() {
    }

    public EquipamientoPK(int idMusico, int idInstrumento) {
        this.idMusico = idMusico;
        this.idInstrumento = idInstrumento;
    }

    public int getIdMusico() {
        return idMusico;
    }

    public void setIdMusico(int idMusico) {
        this.idMusico = idMusico;
    }

    public int getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(int idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMusico;
        hash += (int) idInstrumento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipamientoPK)) {
            return false;
        }
        EquipamientoPK other = (EquipamientoPK) object;
        if (this.idMusico != other.idMusico) {
            return false;
        }
        if (this.idInstrumento != other.idInstrumento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.EquipamientoPK[ idMusico=" + idMusico + ", idInstrumento=" + idInstrumento + " ]";
    }
    
}
