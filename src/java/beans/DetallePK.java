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
public class DetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idE")
    private int idE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCaracteristica")
    private int idCaracteristica;

    public DetallePK() {
    }

    public DetallePK(int idE, int idCaracteristica) {
        this.idE = idE;
        this.idCaracteristica = idCaracteristica;
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public int getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(int idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idE;
        hash += (int) idCaracteristica;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePK)) {
            return false;
        }
        DetallePK other = (DetallePK) object;
        if (this.idE != other.idE) {
            return false;
        }
        if (this.idCaracteristica != other.idCaracteristica) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.DetallePK[ idE=" + idE + ", idCaracteristica=" + idCaracteristica + " ]";
    }
    
}
