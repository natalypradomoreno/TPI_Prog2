package Controlador;

import Persistencia.JPAUtil;
import modelo.Gato;
import modelo.HistorialDeAtencion;
import modelo.Tratamiento;
import modelo.Estudios;
import modelo.Veterinario;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/*
 Controlador que maneja la lógica de registro del historial médico.
 Conecta la vista PanelHistorialMedico con las entidades del modelo y la base.
 */
public class ControladorHistorial {

    private final EntityManagerFactory emf;

    public ControladorHistorial() {
        emf = JPAUtil.getEMF();
    }

    /*
     Busca un gato en la base por su código QR.
     */
    public Gato buscarGatoPorCodigo(String codigoQR) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Gato.class, codigoQR);
        } finally {
            em.close();
        }
    }

    /*
     Trae todos los tratamientos cargados para llenar el combo.
     */
    public List<Tratamiento> obtenerTratamientos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Tratamiento t", Tratamiento.class).getResultList();
        } finally {
            em.close();
        }
    }

    /*
     Trae todos los estudios cargados para llenar el combo.
     */
    public List<Estudios> obtenerEstudios() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Estudios e", Estudios.class).getResultList();
        } finally {
            em.close();
        }
    }

    /*
     Guarda un nuevo registro de historial médico en la base.
     */
    public boolean guardarHistorial(String codigoQR, String descripcion,
                                Veterinario vet, String nombreTratamiento,
                                String nombreEstudio, String archivoAdjunto) {
    EntityManager em = emf.createEntityManager();

    try {
        Gato gato = em.find(Gato.class, codigoQR);
        if (gato == null) return false;

        Tratamiento tratamiento = em.createQuery(
                "SELECT t FROM Tratamiento t WHERE t.nombreTratamiento = :nombre", Tratamiento.class)
                .setParameter("nombre", nombreTratamiento)
                .getResultStream().findFirst().orElse(null);

        Estudios estudio = em.createQuery(
                "SELECT e FROM Estudios e WHERE e.nombreEstudio = :nombre", Estudios.class)
                .setParameter("nombre", nombreEstudio)
                .getResultStream().findFirst().orElse(null);

        HistorialDeAtencion historial = new HistorialDeAtencion();
        historial.setFecha(java.sql.Date.valueOf(LocalDate.now()));
        historial.setHora(java.sql.Time.valueOf(LocalTime.now().withNano(0)));
        historial.setDescripcion(descripcion);
        historial.setArchivoAdjunto(archivoAdjunto);
        historial.setVeterinario(vet);
        historial.setGato(gato);
        historial.setTratamiento(tratamiento);
        historial.setEstudio(estudio);

        em.getTransaction().begin();
        em.persist(historial);
        em.getTransaction().commit();
        return true;

    } catch (Exception e) {
        return false;
    } finally {
        em.close();
    }
}

}

