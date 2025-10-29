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
import modelo.Hogar;
import Persistencia.HogarJpaController;
import Persistencia.JPAUtil;

public class ControladorHogares {

    private final HogarJpaController hogarJpa;

    public ControladorHogares() {
        hogarJpa = new HogarJpaController(JPAUtil.getEMF());
    }

    public void crearHogar(Hogar h) {
        hogarJpa.create(h);
    }

    public List<Hogar> listarHogares() {
        return hogarJpa.findHogarEntities();
    }
}
