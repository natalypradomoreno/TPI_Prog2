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
 Panel para registrar o consultar el historial médico de un gato.
 */
public class PanelHistorialMedico extends JPanel {

    private JTextField txtCodigoQR;
    private JTextArea txtDescripcion;
    private JTextField txtFecha;
    private JButton btnGuardar;
    private JButton btnBuscar;

    public PanelHistorialMedico() {
        setLayout(new BorderLayout(10, 10));

        JPanel arriba = new JPanel(new GridLayout(3, 2, 5, 5));
        arriba.add(new JLabel("Código QR del gato:"));
        txtCodigoQR = new JTextField();
        arriba.add(txtCodigoQR);

        arriba.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        arriba.add(txtFecha);

        btnBuscar = new JButton("Buscar Historial");
        arriba.add(new JLabel());
        arriba.add(btnBuscar);

        add(arriba, BorderLayout.NORTH);

        txtDescripcion = new JTextArea(8, 30);
        txtDescripcion.setBorder(BorderFactory.createTitledBorder("Descripción / Diagnóstico"));
        add(new JScrollPane(txtDescripcion), BorderLayout.CENTER);

        btnGuardar = new JButton("Guardar Registro");
        add(btnGuardar, BorderLayout.SOUTH);
    }
}

