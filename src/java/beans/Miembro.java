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
@Table(name = "miembros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miembro.findAll", query = "SELECT m FROM Miembro m"),
    @NamedQuery(name = "Miembro.findByIdE", query = "SELECT m FROM Miembro m WHERE m.miembroPK.idE = :idE"),
    @NamedQuery(name = "Miembro.findByIdGrupo", query = "SELECT m FROM Miembro m WHERE m.miembroPK.idGrupo = :idGrupo")})
public class Miembro implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MiembroPK miembroPK;

    public Miembro() {
    }

    public Miembro(MiembroPK miembroPK) {
        this.miembroPK = miembroPK;
    }

    public Miembro(int idE, int idGrupo) {
        this.miembroPK = new MiembroPK(idE, idGrupo);
    }

    public MiembroPK getMiembroPK() {
        return miembroPK;
    }

    public void setMiembroPK(MiembroPK miembroPK) {
        this.miembroPK = miembroPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (miembroPK != null ? miembroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miembro)) {
            return false;
        }
        Miembro other = (Miembro) object;
        if ((this.miembroPK == null && other.miembroPK != null) || (this.miembroPK != null && !this.miembroPK.equals(other.miembroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Miembro[ miembroPK=" + miembroPK + " ]";
    }
    
}
