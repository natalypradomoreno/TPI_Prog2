/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author natal
 */
import Controlador.ControladorEstudios;
import javax.swing.*;
import java.awt.*;

public class PanelEstudio extends JPanel {

    private final JTextField txtNombreEstudio;
    private final JTextField txtDescripcion;
    private final JButton btnGuardar;

    private final ControladorEstudios controlador;

    public PanelEstudio() {

        setLayout(new GridLayout(3, 2, 5, 5));

        this.controlador = new ControladorEstudios();

        add(new JLabel("Nombre del Estudio:"));
        txtNombreEstudio = new JTextField();
        add(txtNombreEstudio);

        add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);

        btnGuardar = new JButton("Guardar Estudio");
        add(new JLabel()); // espacio
        add(btnGuardar);

        btnGuardar.addActionListener(e -> guardarEstudio());
    }

    private void guardarEstudio() {
        String nombre = txtNombreEstudio.getText().trim();
        String descripcion = txtDescripcion.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Completá el nombre del estudio.",
                    "Faltan datos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean ok = controlador.guardarEstudio(nombre, descripcion);

        if (ok) {
            JOptionPane.showMessageDialog(this,
                    "Estudio guardado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            txtNombreEstudio.setText("");
            txtDescripcion.setText("");

        } else {
            JOptionPane.showMessageDialog(this,
                    "Ya existe un estudio con ese nombre.",
                    "Duplicado",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

