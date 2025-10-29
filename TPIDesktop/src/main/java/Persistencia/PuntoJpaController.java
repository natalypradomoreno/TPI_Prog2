/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author natal
 */

import modelo.PuntoDeAvistamiento;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class PuntoJpaController implements Serializable {

    public PuntoJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public PuntoJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(PuntoDeAvistamiento obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(PuntoDeAvistamiento obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); PuntoDeAvistamiento o = em.getReference(PuntoDeAvistamiento.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<PuntoDeAvistamiento> findPuntoDeAvistamientoEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM PuntoDeAvistamiento o"); List<PuntoDeAvistamiento> l = q.getResultList(); em.close(); return l; }

    public PuntoDeAvistamiento findPuntoDeAvistamiento(int id) { EntityManager em = getEntityManager(); PuntoDeAvistamiento o = em.find(PuntoDeAvistamiento.class, id); em.close(); return o; }

    public int getPuntoDeAvistamientoCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM PuntoDeAvistamiento o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}
