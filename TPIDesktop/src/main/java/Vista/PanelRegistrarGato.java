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
 Panel donde el voluntario registra un gato nuevo.
 */
public class PanelRegistrarGato extends JPanel {

    private JTextField txtCodigoQR;
    private JTextField txtNombre;
    private JTextField txtColor;
    private JTextField txtZona;
    private JComboBox<String> cbEstado;
    private JButton btnGuardar;

    public PanelRegistrarGato() {
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Código QR:"));
        txtCodigoQR = new JTextField();
        add(txtCodigoQR);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Color:"));
        txtColor = new JTextField();
        add(txtColor);

        add(new JLabel("Zona:"));
        txtZona = new JTextField();
        add(txtZona);

        add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(new String[]{"Sano", "En tratamiento", "Esterilizado", "Enfermo"});
        add(cbEstado);

        btnGuardar = new JButton("Guardar Gato");
        add(new JLabel());
        add(btnGuardar);
    }
}

