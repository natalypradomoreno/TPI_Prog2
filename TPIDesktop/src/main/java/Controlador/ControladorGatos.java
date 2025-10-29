/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

package Controlador;

import Vista.VListarPedidos;
import Vista.VListarProductos;
import Vista.VListarUsuarios;


// controladora logica
public class ControladoraAdmin {

    //metodos intermediarios entre el modelo y las vistas
    public void AbrirVProductos() {
        VListarProductos ventana = new VListarProductos();
        ventana.setVisible(true);
    }

    public void AbrirVPedidos() {
        VListarPedidos ventanaP = new VListarPedidos();
        ventanaP.setVisible(true);
    }

    public void AbrirVUsuarios() {
        VListarUsuarios ventanaU = new VListarUsuarios();
        ventanaU.setVisible(true);
    }

}
 */

package Controlador;

import java.util.List;
import modelo.Gato;
import Persistencia.GatoJpaController;
import Persistencia.JPAUtil;
import Persistencia.exceptions.NonexistentEntityException;

public class ControladorGatos {

    private final GatoJpaController gatoJpa;

    public ControladorGatos() {
        gatoJpa = new GatoJpaController(JPAUtil.getEMF());
    }

    public void crearGato(Gato gato) {
        gatoJpa.create(gato);
    }

    public void editarGato(Gato gato) throws Exception {
        gatoJpa.edit(gato);
    }

    public void eliminarGato(String codigoQR) throws NonexistentEntityException {
        gatoJpa.destroy(codigoQR);
    }

    public Gato buscarPorCodigo(String codigoQR) {
        return gatoJpa.findGato(codigoQR);
    }

    public List<Gato> listarGatos() {
        return gatoJpa.findGatoEntities();

    }
}