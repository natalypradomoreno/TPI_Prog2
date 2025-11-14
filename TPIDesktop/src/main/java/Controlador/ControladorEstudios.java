package Controlador;

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
            // validar si existe uno con el mismo nombre
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

            // crear estudio
            Estudios nuevo = new Estudios();
            nuevo.setNombreEstudio(nombre);
            nuevo.setDescripcion(descripcion);

            // guardar
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


