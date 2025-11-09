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
import Persistencia.TareaJpaController;
import Persistencia.VoluntarioJpaController;
import modelo.Tarea;
import modelo.Voluntario;
import java.sql.Date;
import java.sql.Time;

/*
 Controlador lógico para manejar las tareas registradas por los voluntarios.
 */
public class ControladorTareas {

    private final TareaJpaController tareaJpa;
    private final VoluntarioJpaController voluntarioJpa;

    public ControladorTareas() {
        tareaJpa = new TareaJpaController(JPAUtil.getEMF());
        voluntarioJpa = new VoluntarioJpaController(JPAUtil.getEMF());
    }

    /*
     Guarda una nueva tarea en la base de datos.
     Retorna true si se registró correctamente, false si hubo error.
     */
    public boolean guardarTarea(String tipo, String descripcion, Date fecha, Time hora,
                                String codigoQR, int idVoluntario, String zona) {
        try {
            Tarea t = new Tarea();
            t.setTipoTarea(tipo);
            t.setDescripcion(descripcion);
            t.setFecha(fecha);
            t.setHora(hora);
            t.setGatoCodigoQR(codigoQR);
            t.setUbicacion(zona);
            t.setEstado("pendiente"); // o "en curso", según tus reglas
            t.setVoluntarioIdUsuario(idVoluntario);

            tareaJpa.create(t);
            System.out.println("DEBUG >> Tarea guardada correctamente.");
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar tarea: " + e.getMessage());
            return false;
        }
    }

    /*
     Verifica si un voluntario existe en la base de datos por su ID.
     */
    public boolean verificarVoluntario(int id) {
        try {
            Voluntario v = voluntarioJpa.findVoluntario(id);
            return v != null;
        } catch (Exception e) {
            System.out.println("Error al verificar voluntario: " + e.getMessage());
            return false;
        }
    }
}
