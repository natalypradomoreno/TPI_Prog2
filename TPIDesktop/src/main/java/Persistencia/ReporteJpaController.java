/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author natal
 */

import modelo.Reporte;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class ReporteJpaController implements Serializable {

    public ReporteJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public ReporteJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }
    private EntityManagerFactory emf = null;
    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    public void create(Reporte obj) { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit(); em.close(); }

    public void edit(Reporte obj) throws NonexistentEntityException, Exception { EntityManager em = getEntityManager(); em.getTransaction().begin(); em.merge(obj); em.getTransaction().commit(); em.close(); }

    public void destroy(int id) throws NonexistentEntityException { EntityManager em = getEntityManager(); em.getTransaction().begin(); Reporte o = em.getReference(Reporte.class, id); em.remove(o); em.getTransaction().commit(); em.close(); }

    public List<Reporte> findReporteEntities() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT o FROM Reporte o"); List<Reporte> l = q.getResultList(); em.close(); return l; }

    public Reporte findReporte(int id) { EntityManager em = getEntityManager(); Reporte o = em.find(Reporte.class, id); em.close(); return o; }

    public int getReporteCount() { EntityManager em = getEntityManager(); Query q = em.createQuery("SELECT COUNT(o) FROM Reporte o"); Long c = (Long) q.getSingleResult(); em.close(); return c.intValue(); }
}
