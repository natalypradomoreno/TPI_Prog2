/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author natal
 */

import modelo.Tarea;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class TareaJpaController implements Serializable {

    public TareaJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public TareaJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Tarea obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Tarea obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Tarea o = em.getReference(Tarea.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Tarea> findTareaEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Tarea o"); List<Tarea> l = q.getResultList(); em.close(); return l; }

    public Tarea findTarea(int id) { EntityManager em = getEntityManager(); Tarea o = em.find(Tarea.class, id); em.close(); return o; }

    public int getTareaCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Tarea o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}
