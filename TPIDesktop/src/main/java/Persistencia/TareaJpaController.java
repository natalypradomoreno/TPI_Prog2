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

    private EntityManagerFactory emf = null;

    // --- CONSTRUCTORES ---
    public TareaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public TareaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("TPIPU");
    }

    // --- OBTENER ENTITY MANAGER ---
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // --- CREAR ---
    public void create(Tarea tarea) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tarea);
            em.getTransaction().commit();
            System.out.println("DEBUG >> Tarea creada con ID: " + tarea.getIdTarea());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Error al crear tarea: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    // --- EDITAR ---
    public void edit(Tarea tarea) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (findTarea(tarea.getIdTarea()) == null) {
                throw new NonexistentEntityException("La tarea con ID " + tarea.getIdTarea() + " no existe.");
            }
            em.merge(tarea);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // --- ELIMINAR ---
    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Tarea tarea = em.find(Tarea.class, id);
            if (tarea == null) {
                throw new NonexistentEntityException("La tarea con ID " + id + " no existe.");
            }
            em.remove(tarea);
            em.getTransaction().commit();
        } catch (NonexistentEntityException e) {
            throw e;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar la tarea: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // --- LISTAR TODAS ---
    public List<Tarea> findTareaEntities() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT t FROM Tarea t");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    // --- BUSCAR POR ID ---
    public Tarea findTarea(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarea.class, id);
        } finally {
            em.close();
        }
    }

    // --- CONTAR ---
    public int getTareaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT(t) FROM Tarea t");
            Long count = (Long) q.getSingleResult();
            return count.intValue();
        } finally {
            em.close();
        }
    }
}
