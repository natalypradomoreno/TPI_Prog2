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
import modelo.Zona;
import Persistencia.ZonaJpaController;
import Persistencia.JPAUtil;

public class ControladorZonas {

    private final ZonaJpaController zonaJpa;

    public ControladorZonas() {
        zonaJpa = new ZonaJpaController(JPAUtil.getEMF());
    }

    public void crearZona(Zona z) {
        zonaJpa.create(z);
    }

    public List<Zona> listarZonas() {
        return zonaJpa.findZonaEntities();
    }
}

