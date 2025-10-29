/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author natal
 */

import modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public UsuarioJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Usuario obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Usuario obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Usuario o = em.getReference(Usuario.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Usuario> findUsuarioEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Usuario o"); List<Usuario> l = q.getResultList(); em.close(); return l; }

    public Usuario findUsuario(int id) { EntityManager em = getEntityManager(); Usuario o = em.find(Usuario.class, id); em.close(); return o; }

    public int getUsuarioCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Usuario o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}
