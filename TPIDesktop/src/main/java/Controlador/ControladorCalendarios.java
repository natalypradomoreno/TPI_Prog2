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

