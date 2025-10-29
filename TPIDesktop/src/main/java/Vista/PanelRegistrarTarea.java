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
 Panel donde el voluntario registra una tarea.
 */
public class PanelRegistrarTarea extends JPanel {

    private JComboBox<String> cbTipoTarea;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtUbicacion;
    private JButton btnGuardar;

    public PanelRegistrarTarea() {
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Tipo de tarea:"));
        cbTipoTarea = new JComboBox<>(new String[]{
            "Alimentación", "Captura para castración", "Control veterinario", "Transporte a hogar"});
        add(cbTipoTarea);

        add(new JLabel("Fecha (dd/mm/aaaa):"));
        txtFecha = new JTextField();
        add(txtFecha);

        add(new JLabel("Hora:"));
        txtHora = new JTextField();
        add(txtHora);

        add(new JLabel("Ubicación:"));
        txtUbicacion = new JTextField();
        add(txtUbicacion);

        btnGuardar = new JButton("Registrar Tarea");
        add(new JLabel());
        add(btnGuardar);
    }
}

