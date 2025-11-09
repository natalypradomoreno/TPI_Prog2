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

    private final ControladorGato ctrlGatos = new ControladorGato();
    private final ControladorHistorial ctrlHistorial = new ControladorHistorial();
    private final ControladorVeterinarios ctrlVeterinarios = new ControladorVeterinarios();
    private final ControladorVoluntarios ctrlVoluntarios = new ControladorVoluntarios();
    private final ControladorTareas ctrlTareas = new ControladorTareas();

    public ControladorGato getCtrlGatos() { return ctrlGatos; }
    public ControladorHistorial getCtrlHistorial() { return ctrlHistorial; }
    public ControladorVeterinarios getCtrlVeterinarios() { return ctrlVeterinarios; }
    public ControladorVoluntarios getCtrlVoluntarios() { return ctrlVoluntarios; }
    public ControladorTareas getCtrlTareas() { return ctrlTareas; }
}

