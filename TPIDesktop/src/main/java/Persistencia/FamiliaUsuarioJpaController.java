package Persistencia;

import modelo.FamiliaUsuario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class FamiliaUsuarioJpaController implements Serializable {

    public FamiliaUsuarioJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public FamiliaUsuarioJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(FamiliaUsuario obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(FamiliaUsuario obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); FamiliaUsuario o = em.getReference(FamiliaUsuario.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<FamiliaUsuario> findFamiliaUsuarioEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM FamiliaUsuario o"); List<FamiliaUsuario> l = q.getResultList(); em.close(); return l; }

    public FamiliaUsuario findFamiliaUsuario(int id) { EntityManager em = getEntityManager(); FamiliaUsuario o = em.find(FamiliaUsuario.class, id); em.close(); return o; }

    public int getFamiliaUsuarioCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM FamiliaUsuario o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}

