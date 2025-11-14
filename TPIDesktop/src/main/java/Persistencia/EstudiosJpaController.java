package Persistencia;

import modelo.Estudios;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class EstudiosJpaController implements Serializable {

    public EstudiosJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public EstudiosJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Estudios obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Estudios obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Estudios o = em.getReference(Estudios.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Estudios> findEstudiosEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Estudios o"); List<Estudios> l = q.getResultList(); em.close(); return l; }

    public Estudios findEstudios(int id) { EntityManager em = getEntityManager(); Estudios o = em.find(Estudios.class, id); em.close(); return o; }

    public int getEstudiosCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Estudios o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}
