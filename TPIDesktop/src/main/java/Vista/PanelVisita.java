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
 Panel para registrar una visita de seguimiento o ver las del hogar.
 */
public class PanelVisita extends JPanel {

    private JTextField txtIdVisita;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JComboBox<String> cbEstado;
    private JButton btnGuardar;
    private JButton btnBuscar;

    public PanelVisita() {
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("ID Visita:"));
        txtIdVisita = new JTextField();
        add(txtIdVisita);

        add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        add(txtFecha);

        add(new JLabel("Hora:"));
        txtHora = new JTextField();
        add(txtHora);

        add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(new String[]{"Pendiente", "Completada"});
        add(cbEstado);

        btnGuardar = new JButton("Guardar Visita");
        btnBuscar = new JButton("Buscar Visita");
        add(btnBuscar);
        add(btnGuardar);
    }
}
