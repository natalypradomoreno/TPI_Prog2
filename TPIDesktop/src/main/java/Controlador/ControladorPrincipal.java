/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author natal
 */
public class ControladorPrincipal {

    private final ControladorGatos ctrlGatos = new ControladorGatos();
    private final ControladorHistoriales ctrlHistoriales = new ControladorHistoriales();
    private final ControladorVeterinarios ctrlVeterinarios = new ControladorVeterinarios();
    private final ControladorVoluntarios ctrlVoluntarios = new ControladorVoluntarios();
    private final ControladorTareas ctrlTareas = new ControladorTareas();

    public ControladorGatos getCtrlGatos() { return ctrlGatos; }
    public ControladorHistoriales getCtrlHistoriales() { return ctrlHistoriales; }
    public ControladorVeterinarios getCtrlVeterinarios() { return ctrlVeterinarios; }
    public ControladorVoluntarios getCtrlVoluntarios() { return ctrlVoluntarios; }
    public ControladorTareas getCtrlTareas() { return ctrlTareas; }
}

