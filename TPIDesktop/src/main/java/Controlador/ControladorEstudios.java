/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author natal
 */


import Persistencia.EstudiosJpaController;
import Persistencia.JPAUtil;
import modelo.Estudios;

import javax.persistence.EntityManager;
import java.util.List;

public class ControladorEstudios {

    private final EstudiosJpaController estudiosJpa;

    public ControladorEstudios() {
        estudiosJpa = new EstudiosJpaController(JPAUtil.getEMF());
    }

    public boolean guardarEstudio(String nombre, String descripcion) {
        EntityManager em = estudiosJpa.getEntityManager();

        try {
            // Validar si existe uno con el mismo nombre
            Estudios existente = em.createQuery(
                    "SELECT e FROM Estudios e WHERE e.nombreEstudio = :nombre",
                    Estudios.class)
                    .setParameter("nombre", nombre)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (existente != null) {
                return false; // duplicado
            }

            // Crear estudio
            Estudios nuevo = new Estudios();
            nuevo.setNombreEstudio(nombre);
            nuevo.setDescripcion(descripcion);

            // Guardar
            estudiosJpa.create(nuevo);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            em.close();
        }
    }

    public List<Estudios> listarTodos() {
        return estudiosJpa.findEstudiosEntities();
    }
}


