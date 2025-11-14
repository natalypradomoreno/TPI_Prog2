package Persistencia;

import modelo.HistorialDeAtencion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class HistorialMedicoJpaController implements Serializable {

    public HistorialMedicoJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public HistorialMedicoJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(HistorialDeAtencion obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(HistorialDeAtencion obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); HistorialDeAtencion o = em.getReference(HistorialDeAtencion.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<HistorialDeAtencion> findHistorialDeAtencionEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM HistorialDeAtencion o"); List<HistorialDeAtencion> l = q.getResultList(); em.close(); return l; }

    public HistorialDeAtencion findHistorialDeAtencion(int id) { EntityManager em = getEntityManager(); HistorialDeAtencion o = em.find(HistorialDeAtencion.class, id); em.close(); return o; }

    public int getHistorialDeAtencionCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM HistorialDeAtencion o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }

    public List<HistorialDeAtencion> findHistorialMedicoEntities() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public HistorialDeAtencion findHistorialMedico(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
