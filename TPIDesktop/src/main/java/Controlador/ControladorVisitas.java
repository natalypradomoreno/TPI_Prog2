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
import modelo.VisitaDeSeguimiento;
import Persistencia.VisitaJpaController;
import Persistencia.JPAUtil;

public class ControladorVisitas {

    private final VisitaJpaController visitaJpa;

    public ControladorVisitas() {
        visitaJpa = new VisitaJpaController(JPAUtil.getEMF());
    }

    public void crearVisita(VisitaDeSeguimiento v) {
        visitaJpa.create(v);
    }

    public List<VisitaDeSeguimiento> listarVisitas() {
        return visitaJpa.findVisitaDeSeguimientoEntities();
    }
}

