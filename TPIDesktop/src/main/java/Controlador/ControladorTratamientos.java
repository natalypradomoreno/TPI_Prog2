/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

package Controlador;

import Modelo.Producto;
import Persistencia.ControladoraPersistencia;
import java.util.List;

/**
 *
 * @author emito

public class ControladorProductos {

    ControladoraPersistencia CtrlPer = new ControladoraPersistencia();
    Producto prod = new Producto();

    //metodos intermediarios entre el controlador de CRUD y la vista
    public List<Producto> LeerProductos() {
        return CtrlPer.LeerProductos();
    }

    public void EscribirProducto(String Nombre, String Descripcion, float Precio,
            int Stock, String Categoria) {
        prod.setNombre_producto(Nombre);
        prod.setDescripcion(Descripcion);
        prod.setPrecio(Precio);
        prod.setStock(Stock);
        prod.setCategoria(Categoria);
        CtrlPer.EscribirProducto(prod);

    }

    public void EliminarProducto(int CodigoProducto) {
        CtrlPer.EliminarProducto(CodigoProducto);
    }

    public Producto BuscarProducto(int CodigoProducto) {
        return prod = CtrlPer.BuscarProducto(CodigoProducto);
    }

    public void EditarProducto(Producto prod, String Nombre, String Descripcion, float Precio, int Stock, String Categoria) {
        prod.setNombre_producto(Nombre);
        prod.setDescripcion(Descripcion);
        prod.setPrecio(Precio);
        prod.setStock(Stock);
        prod.setCategoria(Categoria);
        CtrlPer.EditarProducto(prod);
    }

}
 */

package Controlador;

import java.util.List;
import modelo.Tratamiento;
import Persistencia.TratamientoJpaController;
import Persistencia.JPAUtil;
import Persistencia.exceptions.NonexistentEntityException;

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
}
