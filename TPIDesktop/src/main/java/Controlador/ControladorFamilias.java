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
import modelo.FamiliaUsuario;
import Persistencia.FamiliaUsuarioJpaController;
import Persistencia.JPAUtil;
import Persistencia.exceptions.NonexistentEntityException;

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
}

