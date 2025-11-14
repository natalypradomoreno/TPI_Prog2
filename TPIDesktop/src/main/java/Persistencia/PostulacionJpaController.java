package Persistencia;


import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import Persistencia.exceptions.NonexistentEntityException;
import modelo.Postulacion;

public class PostulacionJpaController implements Serializable {

    public PostulacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public PostulacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("TPIPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Postulacion obj) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        em.close();
    }

    public void edit(Postulacion obj) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
        em.close();
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Postulacion o = em.getReference(Postulacion.class, id);
        em.remove(o);
        em.getTransaction().commit();
        em.close();
    }

    public List<Postulacion> findPostulacionEntities() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT p FROM Postulacion p");
        List<Postulacion> lista = q.getResultList();
        em.close();
        return lista;
    }

    public Postulacion findPostulacion(int id) {
        EntityManager em = getEntityManager();
        Postulacion p = em.find(Postulacion.class, id);
        em.close();
        return p;
    }

    public int getPostulacionCount() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT COUNT(p) FROM Postulacion p");
        Long c = (Long) q.getSingleResult();
        em.close();
        return c.intValue();
    }
}
