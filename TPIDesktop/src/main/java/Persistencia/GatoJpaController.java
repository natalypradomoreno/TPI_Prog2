/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import modelo.Gato;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class GatoJpaController implements Serializable {

    public GatoJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public GatoJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Gato obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Gato obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(String id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Gato o = em.getReference(Gato.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Gato> findGatoEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Gato o"); List<Gato> l = q.getResultList(); em.close(); return l; }

    public Gato findGato(String id) { EntityManager em = getEntityManager(); Gato o = em.find(Gato.class, id); em.close(); return o; }

    public int getGatoCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Gato o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}
