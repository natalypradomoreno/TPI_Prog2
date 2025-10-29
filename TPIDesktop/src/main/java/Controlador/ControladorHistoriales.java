/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
package Controlador;
import Modelo.Pedido;
import Persistencia.ControladoraPersistencia;
import java.util.List;

public class ControladorPedidos {

    ControladoraPersistencia CtrlPer = new ControladoraPersistencia();
    Pedido ped = new Pedido();

    //metodos intermediarios entre el controlador de CRUD y la vista
    public List<Pedido> LeerPedidos() {
        return CtrlPer.LeerPedidos();
    }

    public void EscribirPedido(float Total, String Estado, String Fecha) {
        ped.setFecha(Fecha);
        ped.setEstado(Estado);
        ped.setTotal(Total);
        CtrlPer.EscribirPedido(ped);
    }

    public void EliminarPedido(int id) {
        CtrlPer.EliminarPedido(id);
    }

    public Pedido BuscarPedido(int id) {
        return CtrlPer.BuscarPedido(id);
    }

    public void EditarPedido(Pedido ped, String Estado, String Fecha, float Total) {
        ped.setFecha(Fecha);
        ped.setEstado(Estado);
        ped.setTotal(Total);
        CtrlPer.EditarPedido(ped);
    }
}
 */

package Controlador;

import java.util.List;
import modelo.HistorialDeAtencion;
import Persistencia.HistorialMedicoJpaController;
import Persistencia.JPAUtil;
import Persistencia.exceptions.NonexistentEntityException;

public class ControladorHistoriales {

    private final HistorialMedicoJpaController historialJpa;

    public ControladorHistoriales() {
        historialJpa = new HistorialMedicoJpaController(JPAUtil.getEMF());
    }

    public void crearHistorial(HistorialDeAtencion h) {
        historialJpa.create(h);
    }

    public void editarHistorial(HistorialDeAtencion h) throws Exception {
        historialJpa.edit(h);
    }

    public void eliminarHistorial(int id) throws NonexistentEntityException {
        historialJpa.destroy(id);
    }

    public HistorialDeAtencion buscarPorId(int id) {
        return historialJpa.findHistorialMedico(id);
    }

    public List<HistorialDeAtencion> listarTodos() {
        return historialJpa.findHistorialMedicoEntities();
    }
}
