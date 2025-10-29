/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author natal
 */
import java.util.List;
import modelo.Tarea;
import Persistencia.TareaJpaController;
import Persistencia.JPAUtil;

public class ControladorTareas {

    private final TareaJpaController tareaJpa;

    public ControladorTareas() {
        tareaJpa = new TareaJpaController(JPAUtil.getEMF());
    }

    public void crearTarea(Tarea t) {
        tareaJpa.create(t);
    }

    public List<Tarea> listarTareas() {
        return tareaJpa.findTareaEntities();
    }
}

