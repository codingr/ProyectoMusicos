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
public class MiembroPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idE")
    private int idE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idGrupo")
    private int idGrupo;

    public MiembroPK() {
    }

    public MiembroPK(int idE, int idGrupo) {
        this.idE = idE;
        this.idGrupo = idGrupo;
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idE;
        hash += (int) idGrupo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MiembroPK)) {
            return false;
        }
        MiembroPK other = (MiembroPK) object;
        if (this.idE != other.idE) {
            return false;
        }
        if (this.idGrupo != other.idGrupo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.MiembroPK[ idE=" + idE + ", idGrupo=" + idGrupo + " ]";
    }
    
}
