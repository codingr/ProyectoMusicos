/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

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
                + "substring(nombre,0)=:letra");
        
        
        musicos=q.getResultList();        
        return musicos;
    }
    
}
