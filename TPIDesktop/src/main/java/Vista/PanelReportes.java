/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author natal
 */


import javax.swing.*;
import java.awt.*;

/*
 Panel donde el administrador ve los reportes o estadísticas.
 */
public class PanelReportes extends JPanel {

    private JButton btnVerZonas;
    private JButton btnVerGatos;
    private JButton btnVerTareas;

    public PanelReportes() {
        setLayout(new GridLayout(3, 1, 10, 10));

        btnVerZonas = new JButton("Ver Reporte de Zonas");
        btnVerGatos = new JButton("Ver Reporte de Gatos");
        btnVerTareas = new JButton("Ver Reporte de Tareas");

        add(btnVerZonas);
        add(btnVerGatos);
        add(btnVerTareas);
    }
}

