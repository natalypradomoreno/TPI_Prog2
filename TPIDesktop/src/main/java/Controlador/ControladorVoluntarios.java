package Controlador;

import java.util.List;
import modelo.Voluntario;
import Persistencia.VoluntarioJpaController;
import Persistencia.JPAUtil;
import Persistencia.exceptions.NonexistentEntityException;

public class ControladorVoluntarios {

    private final VoluntarioJpaController voluntarioJpa;

    public ControladorVoluntarios() {
        voluntarioJpa = new VoluntarioJpaController(JPAUtil.getEMF());
    }

    public void crearVoluntario(Voluntario v) {
        voluntarioJpa.create(v);
    }

    public void editarVoluntario(Voluntario v) throws Exception {
        voluntarioJpa.edit(v);
    }

    public void eliminarVoluntario(int id) throws NonexistentEntityException {
        voluntarioJpa.destroy(id);
    }

    public Voluntario buscarPorId(int id) {
        return voluntarioJpa.findVoluntario(id);
    }

    public List<Voluntario> listarTodos() {
        return voluntarioJpa.findVoluntarioEntities();
    }
}

