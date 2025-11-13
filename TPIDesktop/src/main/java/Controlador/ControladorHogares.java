/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author natal
 */

import modelo.Hogar;
import Persistencia.HogarJpaController;
import Persistencia.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import modelo.FamiliaUsuario;

public class ControladorHogares {

    private final HogarJpaController hogarJpa;

    public ControladorHogares() {
        hogarJpa = new HogarJpaController(JPAUtil.getEMF());
    }

    public void crearHogar(Hogar h) {
        hogarJpa.create(h);
    }

    public void editarHogar(Hogar h) throws Exception {
        hogarJpa.edit(h);
    }

    public Hogar buscarPorId(int id) {
        return hogarJpa.findHogar(id);
    }

    public List<Hogar> listarHogares() {
        return hogarJpa.findHogarEntities();
    }

    public List<Hogar> listarPorTipo(String tipo) {
        return hogarJpa.getEntityManager()
                .createQuery("SELECT h FROM Hogar h WHERE h.tipo = :t", Hogar.class)
                .setParameter("t", tipo)
                .getResultList();
    }
 // ---- NUEVO: crear hogar + vincular familia ----
    public Hogar crearHogarVinculado(String direccion, String tipo, FamiliaUsuario fam) {

        EntityManager em = hogarJpa.getEntityManager();

        try {
            em.getTransaction().begin();

            // Crear hogar
            Hogar h = new Hogar();
            h.setDireccion(direccion);
            h.setTipo(tipo);

            em.persist(h);

            // Vincular familia
            fam.setHogar(h);
            em.merge(fam);

            em.getTransaction().commit();

            return h;

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ERROR creando hogar vinculado: " + e.getMessage());
            return null;

        } finally {
            em.close();
        }
    }
}

