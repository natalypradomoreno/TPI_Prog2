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
import modelo.PuntoDeAvistamiento;
import Persistencia.PuntoJpaController;
import Persistencia.JPAUtil;

public class ControladorPuntos {

    private final PuntoJpaController puntoJpa;

    public ControladorPuntos() {
        puntoJpa = new PuntoJpaController(JPAUtil.getEMF());
    }

    public void crearPunto(PuntoDeAvistamiento p) {
        puntoJpa.create(p);
    }

    public List<PuntoDeAvistamiento> listarPuntos() {
        return puntoJpa.findPuntoDeAvistamientoEntities();
    }
}

