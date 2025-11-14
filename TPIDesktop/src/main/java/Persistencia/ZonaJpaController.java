package Persistencia;

import modelo.Zona;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class ZonaJpaController implements Serializable {

    public ZonaJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public ZonaJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Zona obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Zona obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Zona o = em.getReference(Zona.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Zona> findZonaEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Zona o"); List<Zona> l = q.getResultList(); em.close(); return l; }

    public Zona findZona(int id) { EntityManager em = getEntityManager(); Zona o = em.find(Zona.class, id); em.close(); return o; }

    public int getZonaCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Zona o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}

