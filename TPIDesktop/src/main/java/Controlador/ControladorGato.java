/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Persistencia.GatoJpaController;
import Persistencia.JPAUtil;
import modelo.Gato;
import modelo.Zona;
import javax.persistence.*;
import java.util.List;

public class ControladorGato {

    private final GatoJpaController gatoJpa;

    public ControladorGato() {
        gatoJpa = new GatoJpaController(JPAUtil.getEMF());
    }

    public void crearGato(Gato gato) {
        gatoJpa.create(gato);
    }

    public Gato buscarGatoPorCodigo(String codigoQR) {
        return gatoJpa.findGato(codigoQR);
    }

    public List<Gato> listarGatos() {
        return gatoJpa.findGatoEntities();
    }

    // ---- NUEVOS MÉTODOS PARA REPORTES ----
    public List<Gato> listarGatosPorZona(String nombreZona) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPIPU");
        EntityManager em = emf.createEntityManager();
        List<Gato> gatos = em.createQuery(
                "SELECT g FROM Gato g WHERE g.zona.nombreZona = :zona", Gato.class)
                .setParameter("zona", nombreZona)
                .getResultList();
        em.close();
        emf.close();
        return gatos;
    }

    public List<Gato> listarGatosPorEstado(String estado) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TPIPU");
        EntityManager em = emf.createEntityManager();
        List<Gato> gatos = em.createQuery(
                "SELECT g FROM Gato g WHERE g.estado = :estado", Gato.class)
                .setParameter("estado", estado)
                .getResultList();
        em.close();
        emf.close();
        return gatos;
    }

public void editarGato(Gato gato) {
    try {
        gatoJpa.edit(gato);
        System.out.println("Gato actualizado correctamente: " + gato.getCodigoQR());
    } catch (Exception e) {
        System.err.println("Error al editar gato: " + e.getMessage());
        e.printStackTrace();
    }
}
public List<Gato> listarGatosPorSituacion(int situacion) {
    EntityManager em = JPAUtil.getEMF().createEntityManager();

    List<Gato> lista = em.createQuery(
            "SELECT g FROM Gato g WHERE g.situacion = :s", 
            Gato.class)
            .setParameter("s", situacion)
            .getResultList();

    em.close();
    return lista;
}


}
