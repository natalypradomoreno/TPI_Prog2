/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author natal
 */

import modelo.VisitaDeSeguimiento;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class VisitaJpaController implements Serializable {

    public VisitaJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public VisitaJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(VisitaDeSeguimiento obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(VisitaDeSeguimiento obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); VisitaDeSeguimiento o = em.getReference(VisitaDeSeguimiento.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<VisitaDeSeguimiento> findVisitaDeSeguimientoEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM VisitaDeSeguimiento o"); List<VisitaDeSeguimiento> l = q.getResultList(); em.close(); return l; }

    public VisitaDeSeguimiento findVisitaDeSeguimiento(int id) { EntityManager em = getEntityManager(); VisitaDeSeguimiento o = em.find(VisitaDeSeguimiento.class, id); em.close(); return o; }

    public int getVisitaDeSeguimientoCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM VisitaDeSeguimiento o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}
