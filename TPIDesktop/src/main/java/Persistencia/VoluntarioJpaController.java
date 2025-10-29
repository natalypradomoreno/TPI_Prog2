/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author natal
 */

import modelo.Voluntario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class VoluntarioJpaController implements Serializable {

    public VoluntarioJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public VoluntarioJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Voluntario obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Voluntario obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Voluntario o = em.getReference(Voluntario.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Voluntario> findVoluntarioEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Voluntario o"); List<Voluntario> l = q.getResultList(); em.close(); return l; }

    public Voluntario findVoluntario(int id) { EntityManager em = getEntityManager(); Voluntario o = em.find(Voluntario.class, id); em.close(); return o; }

    public int getVoluntarioCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Voluntario o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}

