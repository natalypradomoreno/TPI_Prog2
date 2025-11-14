package Controlador;

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

