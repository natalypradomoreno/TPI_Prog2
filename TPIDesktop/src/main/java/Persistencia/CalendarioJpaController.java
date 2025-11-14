/*
 este es el controlador jpa de calendario. basicamente es la clase que se encarga
 de hablar con la base de datos usando jpa para todo lo relacionado a la tabla calendario.

 no tiene logica de negocio ni nada raro, solo crud puro.
 es el tipico archivo que genera netbeans, pero aca lo usamos en visitas porque
 cada visita crea un calendario nuevo con la fecha.
*/
package Persistencia;

import modelo.Calendario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import Persistencia.exceptions.NonexistentEntityException;

public class CalendarioJpaController implements Serializable {

    /*
     el emf es el entitymanagerfactory que jpa usa para abrir conexiones
     hay dos constructores:
     - uno que recibe un emf (lo usamos cuando viene de JPAUtil)
     - otro que crea uno nuevo directo
    */
    public CalendarioJpaController(EntityManagerFactory emf) { this.emf = emf; }
    public CalendarioJpaController() { emf = Persistence.createEntityManagerFactory("TPIPU"); }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() { return emf.createEntityManager(); }

    /*
     create: abre transaccion, guarda un calendario y cierra
     esto se usa cuando registramos una visita nueva, porque siempre
     se crea un objeto calendario asociado con la fecha
    */
    public void create(Calendario obj) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
        em.close();
    }

    /*
     edit: permite modificar un calendario ya existente
     no lo usamos mucho en este proyecto, pero queda por si hace falta
    */
    public void edit(Calendario obj) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
        em.close();
    }

    /*
     destroy: elimina un calendario por id
     tampoco lo usamos porque las visitas quedan con su registro historico
    */
    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Calendario o = em.getReference(Calendario.class, id);
        em.remove(o);
        em.getTransaction().commit();
        em.close();
    }

    /*
     metodos de busqueda basicos: listar todos, buscar uno por id,
     y contar cuantos hay
     estos siempre estan en los controllers generados por netbeans
    */
    public List<Calendario> findCalendarioEntities() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT o FROM Calendario o");
        List<Calendario> l = q.getResultList();
        em.close();
        return l;
    }

    public Calendario findCalendario(int id) {
        EntityManager em = getEntityManager();
        Calendario o = em.find(Calendario.class, id);
        em.close();
        return o;
    }

    public int getCalendarioCount() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT COUNT(o) FROM Calendario o");
        Long c = (Long) q.getSingleResult();
        em.close();
        return c.intValue();
    }
}
