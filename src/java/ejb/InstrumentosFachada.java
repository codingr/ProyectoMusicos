/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import beans.Comentario;
import beans.Elemento;
import beans.Grupo;
import beans.Instrumento;
import beans.Musico;
import beans.Usuario;
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
                    + "substring(m.apellido,1,1)=:letra order by m.nombre");
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
        List<Instrumento> instrumentos=null;
        Query q;
        if (letra == null) {
            q = em.createNamedQuery("Instrumento.findAll");

        } else {
            
            q = em.createQuery("select i from Instrumento i where "
                    + "substring(i.marca,1,1)=:letra order by i.marca");
            q.setParameter("letra", letra);
        }

        try {
            instrumentos = q.getResultList();
        } catch (NoResultException ex) {
            instrumentos = null;
        }catch(Exception ex){
            ex.getMessage();
        }
        return instrumentos;
    }

    public List<Comentario> getComentarios(Elemento elemento){
        List<Comentario> comentarios;
        Query q=em.createQuery("SELECT c FROM Comentario c WHERE c.idE = :idE");
        q.setParameter("idE", elemento);
        try{
            comentarios=q.getResultList();
        }catch (NoResultException ex){
            comentarios=null;
        }
        
        return comentarios;
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

    /**
     *
     * @param id
     * @return
     */
    public Instrumento buscarInstrumento(int id) {
        Instrumento instrumento;
        Query q = em.createNamedQuery("Instrumento.findByIdE");
        q.setParameter("idE", id);
        instrumento = (Instrumento) q.getSingleResult();
        return instrumento;
    }
    
    public Comentario buscarComentario (int id){
        Comentario comentario;
        Query q=em.createNamedQuery("Comentario.findByIdcomentario");
        q.setParameter("idcomentario", id);
        comentario=(Comentario) q.getSingleResult();        
        return comentario;
    }
    
    
    /**
     * 
     * @param comentario 
     */
    public void añadirComentario(Comentario comentario) {
        em.persist(comentario);
    }
    /**
     * 
     * @param usuario 
     */
    public void añadirUsuario(Usuario usuario) {
        //em.getTransaction().begin();
        em.persist(usuario);
        //em.getTransaction().commit();
    }

    /**
     *
     * @param musico
     */
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
    public void actualizarComentario(Comentario comentario){
        em.merge(comentario);
    }
    /**
     *
     * @param nombre
     * @return
     */
    public Usuario buscarUsuario(String nombre) {
        Usuario usuario;
        Query q = em.createNamedQuery("Usuario.findByNombre");
        q.setParameter("nombre", nombre);
        try {
            usuario = (Usuario) q.getSingleResult();
        } catch (NoResultException ex) {
            usuario = null;
        }
        return usuario;
    }

    public void actualizarFechaUltimaConexionUsuario(Usuario usuario) {
        Query q = em.createQuery(
                "update Usuario u set u.fechaultimaconexion=:fechaultimaconexion where u.idusuario=:idusuario");
        q.setParameter("fechaultimaconexion", usuario.getFechaultimaconexion());
        q.setParameter("idusuario", usuario.getIdusuario());
        q.executeUpdate();
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public void persist1(Object object) {
        em.persist(object);
    }

}
