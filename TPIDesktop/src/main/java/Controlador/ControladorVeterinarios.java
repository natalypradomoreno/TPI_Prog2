package Controlador;

import java.util.List;
import modelo.Veterinario;
import Persistencia.VeterinarioJpaController;
import Persistencia.JPAUtil;
import Persistencia.exceptions.NonexistentEntityException;

public class ControladorVeterinarios {

    private final VeterinarioJpaController veterinarioJpa;

    public ControladorVeterinarios() {
        veterinarioJpa = new VeterinarioJpaController(JPAUtil.getEMF());
    }

    public void crearVeterinario(Veterinario v) {
        veterinarioJpa.create(v);
    }

    public void editarVeterinario(Veterinario v) throws Exception {
        veterinarioJpa.edit(v);
    }

    public void eliminarVeterinario(int id) throws NonexistentEntityException {
        veterinarioJpa.destroy(id);
    }

    public Veterinario buscarPorId(int id) {
        return veterinarioJpa.findVeterinario(id);
    }

    public List<Veterinario> listarTodos() {
        return veterinarioJpa.findVeterinarioEntities();
    }
}
