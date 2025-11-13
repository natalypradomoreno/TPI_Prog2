package Controlador;

import Persistencia.JPAUtil;
import Persistencia.VisitaJpaController;
import Persistencia.CalendarioJpaController;
import Persistencia.HogarJpaController;
import modelo.VisitaDeSeguimiento;
import modelo.Calendario;
import modelo.Hogar;

import java.sql.Date;
import java.util.List;

// este controlador junta toda la logica que tiene que ver con las visitas
// basicamente conecta las vistas con los jpa que guardan cosas en la base
public class ControladorVisitas {

    private final VisitaJpaController visitaJpa;
    private final CalendarioJpaController calendarioJpa;
    private final HogarJpaController hogarJpa;

    public ControladorVisitas() {
        // aca simplemente se cargan los jpa con el emf del proyecto
        // esto es lo que despues permite guardar o editar cosas en la base
        visitaJpa = new VisitaJpaController(JPAUtil.getEMF());
        calendarioJpa = new CalendarioJpaController(JPAUtil.getEMF());
        hogarJpa = new HogarJpaController(JPAUtil.getEMF());
    }

    // buscar un hogar por id para poder asignarlo a una visita
    // es mas prolijo manejarlo asi que con texto
    public Hogar buscarHogar(int id) {
        return hogarJpa.findHogar(id);
    }

    // crear una visita nueva
    // este metodo arma dos objetos, uno para calendario y otro para la visita
    // porque la tabla visita esta ligada a calendario
    public boolean crearVisita(String estado, Date fecha, String ubicacion, Hogar hogar) {
        try {
            // primero se crea el registro en calendario
            Calendario cal = new Calendario();
            cal.setFecha(fecha);
            calendarioJpa.create(cal);

            // despues se arma la visita con todos los datos
            VisitaDeSeguimiento v = new VisitaDeSeguimiento();
            v.setEstado(estado);
            v.setFecha(fecha);
            v.setUbicacion(ubicacion);
            v.setCalendario(cal);
            v.setHogar(hogar);

            // y se guarda
            visitaJpa.create(v);
            return true;

        } catch (Exception e) {
            System.err.println("error al registrar visita " + e.getMessage());
            return false;
        }
    }

    // traer solo las visitas que estan pendientes
    // se usa en el panel donde el voluntario cambia el estado
    public List<VisitaDeSeguimiento> obtenerVisitasPendientes() {
        List<VisitaDeSeguimiento> todas = visitaJpa.findVisitaDeSeguimientoEntities();

        // se filtra simplemente por estado pendiente
        return todas.stream()
                .filter(v -> v.getEstado() != null &&
                             v.getEstado().equalsIgnoreCase("pendiente"))
                .toList();
    }

    // cambiar el estado de una visita cuando ya se tiene el objeto cargado
    public boolean cambiarEstadoVisita(VisitaDeSeguimiento visita, String nuevoEstado) {
        try {
            visita.setEstado(nuevoEstado);
            visitaJpa.edit(visita);
            return true;

        } catch (Exception e) {
            System.err.println("error al cambiar estado visita " + e.getMessage());
            return false;
        }
    }

    // cambiar estado usando el id de la visita
    // este es el que usa el panel del jlist porque trabaja por indice
    public boolean actualizarEstadoVisita(int idVisita, String nuevoEstado) {
        try {
            // se busca la visita real en la base por si algo cambio
            VisitaDeSeguimiento v = visitaJpa.findVisitaDeSeguimiento(idVisita);

            if (v == null) {
                return false;
            }

            v.setEstado(nuevoEstado);
            visitaJpa.edit(v);
            return true;

        } catch (Exception e) {
            System.err.println("error al actualizar estado visita " + e.getMessage());
            return false;
        }
    }
}
