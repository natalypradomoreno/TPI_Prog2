package Controlador;

import java.util.List;
import modelo.Tratamiento;
import Persistencia.TratamientoJpaController;
import Persistencia.JPAUtil;
import Persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;

public class ControladorTratamientos {

    private final TratamientoJpaController tratamientoJpa;

    public ControladorTratamientos() {
        tratamientoJpa = new TratamientoJpaController(JPAUtil.getEMF());
    }

    public void crearTratamiento(Tratamiento t) {
        tratamientoJpa.create(t);
    }

    public void editarTratamiento(Tratamiento t) throws Exception {
        tratamientoJpa.edit(t);
    }

    public void eliminarTratamiento(int id) throws NonexistentEntityException {
        tratamientoJpa.destroy(id);
    }

    public Tratamiento buscarPorId(int id) {
        return tratamientoJpa.findTratamiento(id);
    }

    public List<Tratamiento> listarTodos() {
        return tratamientoJpa.findTratamientoEntities();
    }

  public boolean guardarTratamiento(String nombre, String medicamento) {
    EntityManager em = tratamientoJpa.getEntityManager(); 

    try {
        // verificar si existe un tratamiento con ese nombre
        Tratamiento existente = em.createQuery(
                "SELECT t FROM Tratamiento t WHERE t.nombreTratamiento = :nombre",
                Tratamiento.class)
                .setParameter("nombre", nombre)
                .getResultStream()
                .findFirst()
                .orElse(null);

        if (existente != null) {
            return false; // ya existe entonces no lo guardamos
        }

        // crear nuevo tratamiento
        Tratamiento t = new Tratamiento();
        t.setNombreTratamiento(nombre);
        t.setMedicamento(medicamento);

        // guardar mediante el JpaController
        tratamientoJpa.create(t);

        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;

    } finally {
        em.close();
    }
}




}
