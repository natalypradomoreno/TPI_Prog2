package Controlador;

import java.util.List;
import modelo.Calendario;
import Persistencia.CalendarioJpaController;
import Persistencia.JPAUtil;

public class ControladorCalendarios {

    private final CalendarioJpaController calendarioJpa;

    public ControladorCalendarios() {
        calendarioJpa = new CalendarioJpaController(JPAUtil.getEMF());
    }

    public void crearCalendario(Calendario c) {
        calendarioJpa.create(c);
    }

    public List<Calendario> listarCalendarios() {
        return calendarioJpa.findCalendarioEntities();
    }
}

