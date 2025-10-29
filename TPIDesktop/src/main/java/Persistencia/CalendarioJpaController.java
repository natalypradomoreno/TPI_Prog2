/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author natal
 */

import modelo.Calendario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class CalendarioJpaController implements Serializable {

    public CalendarioJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public CalendarioJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Calendario obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Calendario obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Calendario o = em.getReference(Calendario.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Calendario> findCalendarioEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Calendario o"); List<Calendario> l = q.getResultList(); em.close(); return l; }

    public Calendario findCalendario(int id) { EntityManager em = getEntityManager(); Calendario o = em.find(Calendario.class, id); em.close(); return o; }

    public int getCalendarioCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Calendario o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}

