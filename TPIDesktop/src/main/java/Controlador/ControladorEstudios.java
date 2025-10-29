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
import modelo.Estudios;
import Persistencia.EstudiosJpaController;
import Persistencia.JPAUtil;
import Persistencia.exceptions.NonexistentEntityException;

public class ControladorEstudios {

    private final EstudiosJpaController estudiosJpa;

    public ControladorEstudios() {
        estudiosJpa = new EstudiosJpaController(JPAUtil.getEMF());
    }

    public void crearEstudio(Estudios e) {
        estudiosJpa.create(e);
    }

    public void editarEstudio(Estudios e) throws Exception {
        estudiosJpa.edit(e);
    }

    public void eliminarEstudio(int id) throws NonexistentEntityException {
        estudiosJpa.destroy(id);
    }

    public Estudios buscarPorId(int id) {
        return estudiosJpa.findEstudios(id);
    }

    public List<Estudios> listarTodos() {
        return estudiosJpa.findEstudiosEntities();
    }
}

