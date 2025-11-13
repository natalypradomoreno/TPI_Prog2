/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author natal
 */

import Persistencia.JPAUtil;
import Persistencia.PostulacionJpaController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import modelo.Postulacion;
import modelo.Gato;
import modelo.Hogar;
import modelo.FamiliaUsuario;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ControladorPostulaciones {

    private final EntityManagerFactory emf;
    private final PostulacionJpaController postJpa;

    public ControladorPostulaciones() {
        emf = JPAUtil.getEMF();
        postJpa = new PostulacionJpaController(emf);
    }

    /*
     Crear una postulación.
     Devuelve true si se pudo guardar correctamente.
     */
  public boolean crearPostulacion(Hogar hogar, Gato gato, FamiliaUsuario fam, int tipoPostulacion) {
    EntityManager em = emf.createEntityManager();

    try {
        // Verificar si ya existe una postulación pendiente de ESTA familia para ESTE gato
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(p) FROM Postulacion p " +
            "WHERE p.usuario.idUsuario = :idFam " +
            "AND p.gato.codigoQR = :idGato " +
            "AND p.estado = 0",
            Long.class
        );

        query.setParameter("idFam", fam.getIdUsuario());
        query.setParameter("idGato", gato.getCodigoQR());

        Long existe = query.getSingleResult();
        if (existe > 0) {
            return false; // ya existe una postulación pendiente para este mismo gato
        }

        // Crear nueva postulación
        Postulacion p = new Postulacion();
        p.setEstado(0); // pendiente
        p.setFecha(Date.valueOf(LocalDate.now()));
        p.setHogar(hogar);
        p.setGato(gato);
        p.setUsuario(fam);
        p.setTipoPostulacion(tipoPostulacion);

        postJpa.create(p);
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;

    } finally {
        em.close();
    }
}


    /*
     Lista todas las postulaciones pendientes.
     */
    public List<Postulacion> listarPendientes() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery(
                "SELECT p FROM Postulacion p WHERE p.estado = 0",
                Postulacion.class
            ).getResultList();

        } finally {
            em.close();
        }
    }

    /*
     Buscar una postulación por ID.
     */
    public Postulacion buscarPorId(int id) {
        return postJpa.findPostulacion(id);
    }

    /*
     Aceptar una postulación.
     */
    public boolean aceptarPostulacion(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            Postulacion p = em.find(Postulacion.class, id);
            if (p == null) return false;

            p.setEstado(1); // aceptada

            postJpa.edit(p);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            em.close();
        }
    }
    /*
 Aceptar una postulación desde el objeto Postulacion directamente.
*/
public boolean aceptar(Postulacion p) {
    try {
        p.setEstado(1); // aceptada
        postJpa.edit(p);
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    

    /*
     Método cancelar (no cambia nada en la BD).
     */
    public void cancelar() {
        // Método vacío intencionalmente
    }
}
