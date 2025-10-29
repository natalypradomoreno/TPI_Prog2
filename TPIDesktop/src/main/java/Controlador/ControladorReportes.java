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
import modelo.Reporte;
import Persistencia.ReporteJpaController;
import Persistencia.JPAUtil;

public class ControladorReportes {

    private final ReporteJpaController reporteJpa;

    public ControladorReportes() {
        reporteJpa = new ReporteJpaController(JPAUtil.getEMF());
    }

    public void crearReporte(Reporte r) {
        reporteJpa.create(r);
    }

    public List<Reporte> listarReportes() {
        return reporteJpa.findReporteEntities();
    }
}

