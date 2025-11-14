/*
 controlador jpa de la entidad VisitaDeSeguimiento.
 este archivo es el que se encarga de hablar directamente con la base usando jpa.
 aca no hay logica del sistema, solo operaciones basicas de crud (create, read, update, delete).
*/
package Persistencia;

import modelo.VisitaDeSeguimiento;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class VisitaJpaController implements Serializable {

    /*
     el constructor guarda la factory que se usa para crear los entity managers
     esto es lo que permite abrir conexiones a la base cuando hace falta
    */
    public VisitaJpaController(EntityManagerFactory emf) { this.emf = emf; }

    /*
     constructor por defecto, crea la factory desde la unidad de persistencia TPIPU
     esto lo usa casi todo el sistema porque es la forma rapida de levantar el controlador
    */
    public VisitaJpaController() { 
        emf = Persistence.createEntityManagerFactory("TPIPU"); 
    }

    private EntityManagerFactory emf = null;

    /*
     metodo helper para obtener un entity manager nuevo
     cada operacion abre uno y lo cierra al final
    */
    public EntityManager getEntityManager() { 
        return emf.createEntityManager(); 
    }

    /*
     create: guarda una visita nueva en la base
     abre transaccion, persiste el objeto, y la cierra
     no mete logica, solo inserta lo que le pases
    */
    public void create(VisitaDeSeguimiento obj) { 
        EntityManager em = getEntityManager(); 
        em.getTransaction().begin(); 
        em.persist(obj); 
        em.getTransaction().commit(); 
        em.close(); 
    }

    /*
     edit: modifica una visita ya existente
     no hace comprobaciones, simplemente hace merge del objeto
     si el id no existe, tira la excepcion NonexistentEntityException
    */
    public void edit(VisitaDeSeguimiento obj) 
            throws NonexistentEntityException, Exception { 
        EntityManager em = getEntityManager(); 
        em.getTransaction().begin(); 
        em.merge(obj); 
        em.getTransaction().commit(); 
        em.close(); 
    }

    /*
     destroy: elimina una visita de la base usando su id
     usa getReference para no cargar todo el objeto, solo una referencia
    */
    public void destroy(int id) 
            throws NonexistentEntityException { 
        EntityManager em = getEntityManager(); 
        em.getTransaction().begin(); 
        VisitaDeSeguimiento o = em.getReference(VisitaDeSeguimiento.class, id); 
        em.remove(o); 
        em.getTransaction().commit(); 
        em.close(); 
    }

    /*
     findVisitaDeSeguimientoEntities: devuelve todas las visitas de la base
     hace un select simple sin filtros
    */
    public List<VisitaDeSeguimiento> findVisitaDeSeguimientoEntities() { 
        EntityManager em = getEntityManager(); 
        Query q = em.createQuery("SELECT o FROM VisitaDeSeguimiento o"); 
        List<VisitaDeSeguimiento> l = q.getResultList(); 
        em.close(); 
        return l; 
    }

    /*
     find por id: busca una visita puntual por su id en la base
     si no existe devuelve null
    */
    public VisitaDeSeguimiento findVisitaDeSeguimiento(int id) { 
        EntityManager em = getEntityManager(); 
        VisitaDeSeguimiento o = em.find(VisitaDeSeguimiento.class, id); 
        em.close(); 
        return o; 
    }

    /*
     devuelve cuantas visitas hay en total en la tabla
     basicamente un select count(*)
    */
    public int getVisitaDeSeguimientoCount() { 
        EntityManager em = getEntityManager(); 
        Query q = em.createQuery("SELECT COUNT(o) FROM VisitaDeSeguimiento o"); 
        Long c = (Long) q.getSingleResult(); 
        em.close(); 
        return c.intValue(); 
    }
}
