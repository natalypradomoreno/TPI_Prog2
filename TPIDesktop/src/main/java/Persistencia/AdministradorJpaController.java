package Persistencia;

import modelo.Administrador;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class AdministradorJpaController implements Serializable {

    public AdministradorJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public AdministradorJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Administrador obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Administrador obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Administrador o = em.getReference(Administrador.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Administrador> findAdministradorEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Administrador o"); List<Administrador> l = q.getResultList(); em.close(); return l; }

    public Administrador findAdministrador(int id) { EntityManager em = getEntityManager(); Administrador o = em.find(Administrador.class, id); em.close(); return o; }

    public int getAdministradorCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Administrador o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}

