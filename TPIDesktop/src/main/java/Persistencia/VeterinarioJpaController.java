/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import modelo.Veterinario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class VeterinarioJpaController implements Serializable {

    public VeterinarioJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public VeterinarioJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Veterinario obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Veterinario obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Veterinario o = em.getReference(Veterinario.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Veterinario> findVeterinarioEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Veterinario o"); List<Veterinario> l = q.getResultList(); em.close(); return l; }

    public Veterinario findVeterinario(int id) { EntityManager em = getEntityManager(); Veterinario o = em.find(Veterinario.class, id); em.close(); return o; }

    public int getVeterinarioCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Veterinario o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}
