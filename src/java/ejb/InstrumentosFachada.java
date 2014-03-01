/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import beans.Comentario;
import beans.Grupo;
import beans.Instrumento;
import beans.Musico;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class InstrumentosFachada {
    @PersistenceContext(unitName = "ProyectoFinalPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Musico> getMusicos(String letra){
        List<Musico> musicos;
        Query q=em.createQuery("select m from Musico m where "
                + "substring(m.nombre,0)=:letra order by m.nombre");
        q.setParameter("letra", letra);
        
        musicos=q.getResultList();        
        return musicos;
    }
    
    
    public void añadirMusico(Musico musico){
        em.persist(musico);      
    }
    
    public void añadirInstrumento(Instrumento instrumento){
        
    }
    public void añadirGrupo(Grupo grupo){
        
    }
    
    public void añadirComentario(Comentario comentario){
        
    }
    
    public void actualizarMusico (Musico musico){
        Query q=em.createQuery(
"UPDATE Musico m set nombre=:nombre, apellido1=:apellido1, apellido2=:apellido2 where idmusico=:musico");
        q.setParameter("nombre",musico.getNombre() );
        q.setParameter("apellido",musico.getApellido() );
        q.setParameter("alias",musico.getAlias());
        
    }
}
