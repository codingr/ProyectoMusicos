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
import javax.persistence.NoResultException;
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
    /**
     *
     * @param letra
     * @return
     */
    public List<Musico> getMusicos(String letra) {
        List<Musico> musicos;
        Query q;
        if (letra == null) {
            q = em.createNamedQuery("Musico.findAll");
        } else {
            q = em.createQuery("select m from Musico m where "
                    + "substring(m.nombre,1,1)=:letra order by m.nombre");
            q.setParameter("letra", letra);
        }

        try {
            musicos = q.getResultList();
        } catch (NoResultException ex) {
            musicos = null;
        }
        return musicos;
    }
/**
 * 
 * @param letra
 * @return 
 */
    public List<Instrumento> getInstrumentos(String letra) {
        List<Instrumento> instrumentos;
        Query q;
        if (letra == null) {
            q = em.createNamedQuery("Instrumento.findAll");

        } else {
            q = em.createQuery("select m from Instrumento m where "
                    + "substring(m.marca,1,1)=:letra order by m.marca");
            q.setParameter("letra", letra);
        }

        try {
            instrumentos = q.getResultList();
        } catch (NoResultException ex) {
            instrumentos = null;
        }
        return instrumentos;
    }

    /**
     *
     * @param id
     * @return
     */
    public Musico buscarMusico(int id) {
        Musico musico;
        Query q = em.createNamedQuery("Musico.findByIdE");
        q.setParameter("idE", id);
        musico = (Musico) q.getSingleResult();
        return musico;
    }

    public Instrumento buscarInstrumento(int id) {
        Instrumento instrumento;
        Query q = em.createNamedQuery("Instrumento.findByIdE");
        q.setParameter("idE", id);
        instrumento = (Instrumento) q.getSingleResult();
        return instrumento;
    }

    /**
     *
     * @param musico
     */
    public void añadirMusico(Musico musico) {
        em.persist(musico);
    }

    public void añadirInstrumento(Instrumento instrumento) {
        em.persist(instrumento);
    }

    public void añadirGrupo(Grupo grupo) {
        em.persist(grupo);
    }

    public void añadirComentario(Comentario comentario) {
        em.persist(comentario);
    }

    public void actualizarMusico(Musico musico) {
        Query q = em.createQuery(
                "UPDATE Musico m set m.nombre=:nombre, m.apellido=:apellido, m.alias=:alias, m.fechadefuncion=:fechadefuncion,"
                + "m.fechanacimiento=:fechanacimiento "
                + "where idE=:idE");
        q.setParameter("nombre", musico.getNombre());
        q.setParameter("apellido", musico.getApellido());
        q.setParameter("alias", musico.getAlias());
        q.setParameter("fechadefuncion", musico.getFechadefuncion());
        q.setParameter("fechanacimiento", musico.getFechanacimiento());
        q.setParameter("urlfoto", musico.getUrlfoto());
    }
}
