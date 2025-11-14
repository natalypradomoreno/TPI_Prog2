package Persistencia;


import modelo.Hogar;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class HogarJpaController implements Serializable {

    public HogarJpaController(EntityManagerFactory emf) { this.emf = emf; }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Hogar obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Hogar obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Hogar o = em.getReference(Hogar.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Hogar> findHogarEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Hogar o"); List<Hogar> l = q.getResultList(); em.close(); return l; }

    public Hogar findHogar(int id) { EntityManager em = getEntityManager(); Hogar o = em.find(Hogar.class, id); em.close(); return o; }

    public int getHogarCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Hogar o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}

