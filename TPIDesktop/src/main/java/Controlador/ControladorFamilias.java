package Controlador;

import modelo.FamiliaUsuario;
import modelo.Hogar;
import Persistencia.FamiliaUsuarioJpaController;
import Persistencia.JPAUtil;
import Persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import java.util.List;

public class ControladorFamilias {

    private final FamiliaUsuarioJpaController familiaJpa;

    public ControladorFamilias() {
        familiaJpa = new FamiliaUsuarioJpaController(JPAUtil.getEMF());
    }

    public void crearFamilia(FamiliaUsuario f) {
        familiaJpa.create(f);
    }

    public void editarFamilia(FamiliaUsuario f) throws Exception {
        familiaJpa.edit(f);
    }

    public void eliminarFamilia(int id) throws NonexistentEntityException {
        familiaJpa.destroy(id);
    }

    public FamiliaUsuario buscarPorId(int id) {
        return familiaJpa.findFamiliaUsuario(id);
    }

    public List<FamiliaUsuario> listarTodos() {
        return familiaJpa.findFamiliaUsuarioEntities();
    }


    public void asignarHogar(FamiliaUsuario fam, Hogar hogar) throws Exception {
        fam.setHogar(hogar);
        familiaJpa.edit(fam);
    }

    public boolean tieneHogar(FamiliaUsuario f) {
        return f.getHogar() != null;
    }

    public FamiliaUsuario buscarPorUsuarioId(int idUsuario) {
        return familiaJpa.findFamiliaUsuario(idUsuario);
    }
}


