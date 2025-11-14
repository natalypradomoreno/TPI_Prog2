package Persistencia;

import modelo.Tratamiento;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class TratamientoJpaController implements Serializable {

    public TratamientoJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public TratamientoJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Tratamiento obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Tratamiento obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Tratamiento o = em.getReference(Tratamiento.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Tratamiento> findTratamientoEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Tratamiento o"); List<Tratamiento> l = q.getResultList(); em.close(); return l; }

    public Tratamiento findTratamiento(int id) { EntityManager em = getEntityManager(); Tratamiento o = em.find(Tratamiento.class, id); em.close(); return o; }

    public int getTratamientoCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Tratamiento o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}

