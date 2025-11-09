/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author natal
 */



import Controlador.ControladorZonas;
import javax.swing.*;
import java.awt.*;

/**
 * Panel para registrar nuevas zonas en el sistema.
 */
public class PanelRegistrarZona extends JPanel {

    private final JTextField txtNombreZona;
    private final JTextField txtUbicacion;
    private final JButton btnGuardar;
    private final JTextArea txtListadoZonas;

    private final ControladorZonas controlador;

    public PanelRegistrarZona() {
        controlador = new ControladorZonas();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Registrar Nueva Zona"));

        // ---- PANEL DE FORMULARIO ----
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 5, 5));

        panelForm.add(new JLabel("Nombre de Zona:"));
        txtNombreZona = new JTextField();
        panelForm.add(txtNombreZona);

        panelForm.add(new JLabel("Ubicación GPS:"));
        txtUbicacion = new JTextField();
        panelForm.add(txtUbicacion);

        btnGuardar = new JButton("Guardar Zona");
        panelForm.add(new JLabel());
        panelForm.add(btnGuardar);

        add(panelForm, BorderLayout.NORTH);

        // ---- LISTADO DE ZONAS ----
        txtListadoZonas = new JTextArea(10, 40);
        txtListadoZonas.setEditable(false);
        txtListadoZonas.setBorder(BorderFactory.createTitledBorder("Zonas Registradas"));
        add(new JScrollPane(txtListadoZonas), BorderLayout.CENTER);

        // ---- EVENTOS ----
        btnGuardar.addActionListener(e -> registrarZona());

        refrescarListado();
    }

    private void registrarZona() {
        String nombre = txtNombreZona.getText().trim();
        String ubicacion = txtUbicacion.getText().trim();

        if (nombre.isEmpty() || ubicacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completá todos los campos.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean ok = controlador.crearZona(nombre, ubicacion);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Zona registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtNombreZona.setText("");
            txtUbicacion.setText("");
            refrescarListado();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la zona.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refrescarListado() {
        var zonas = controlador.listarZonas();
        txtListadoZonas.setText("");
        for (var z : zonas) {
            txtListadoZonas.append("- " + z.getNombreZona() + " (" + z.getUbicacionGPS() + ")\n");
        }
    }
}

