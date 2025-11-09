/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author natal
 */

package Vista;

import Controlador.ControladorHistorial;
import modelo.Gato;
import modelo.Tratamiento;
import modelo.Estudios;
import modelo.Veterinario;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/*
 Panel para que el veterinario registre una nueva atención médica de un gato.
 */
public class PanelHistorialMedico extends JPanel {

    private final JTextField txtCodigoQR;
    private final JTextArea txtDescripcion;
    private final JTextField txtFecha;
    private final JTextField txtHora;
    private final JTextField txtArchivoAdjunto;
    private final JComboBox<String> cbTratamiento;
    private final JComboBox<String> cbEstudio;
    private final JButton btnBuscar;
    private final JButton btnGuardar;

    // Referencia al veterinario logueado
    private final Veterinario veterinarioActual;

    private final ControladorHistorial controlador;

    public PanelHistorialMedico(Veterinario vet) {
        this.veterinarioActual = vet;
        this.controlador = new ControladorHistorial();

        setLayout(new BorderLayout(10, 10));

        // ---------- Panel superior ----------
        JPanel panelArriba = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Código QR + Botón Buscar
        gbc.gridx = 0; gbc.gridy = 0;
        panelArriba.add(new JLabel("Código QR del gato:"), gbc);

        txtCodigoQR = new JTextField(10);
        gbc.gridx = 1; gbc.gridy = 0;
        panelArriba.add(txtCodigoQR, gbc);

        btnBuscar = new JButton("Buscar");
        gbc.gridx = 2; gbc.gridy = 0;
        panelArriba.add(btnBuscar, gbc);

        // Fila 2: Fecha
        gbc.gridx = 0; gbc.gridy = 1;
        panelArriba.add(new JLabel("Fecha:"), gbc);

        txtFecha = new JTextField(LocalDate.now().toString());
        txtFecha.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.gridwidth = 2;
        panelArriba.add(txtFecha, gbc);
        gbc.gridwidth = 1;

        // Fila 3: Hora
        gbc.gridx = 0; gbc.gridy = 2;
        panelArriba.add(new JLabel("Hora:"), gbc);

        txtHora = new JTextField(LocalTime.now().withNano(0).toString());
        txtHora.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelArriba.add(txtHora, gbc);
        gbc.gridwidth = 1;

        // Fila 4: Tratamiento
        gbc.gridx = 0; gbc.gridy = 3;
        panelArriba.add(new JLabel("Tratamiento:"), gbc);

        cbTratamiento = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelArriba.add(cbTratamiento, gbc);
        gbc.gridwidth = 1;

        // Fila 5: Estudio
        gbc.gridx = 0; gbc.gridy = 4;
        panelArriba.add(new JLabel("Estudio:"), gbc);

        cbEstudio = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.gridwidth = 2;
        panelArriba.add(cbEstudio, gbc);
        gbc.gridwidth = 1;

        // Fila 6: Archivo Adjunto
        gbc.gridx = 0; gbc.gridy = 5;
        panelArriba.add(new JLabel("Archivo adjunto:"), gbc);

        txtArchivoAdjunto = new JTextField();
        gbc.gridx = 1; gbc.gridy = 5;
        gbc.gridwidth = 2;
        panelArriba.add(txtArchivoAdjunto, gbc);
        gbc.gridwidth = 1;

        add(panelArriba, BorderLayout.NORTH);

        // ---------- Panel central ----------
        txtDescripcion = new JTextArea(8, 30);
        txtDescripcion.setBorder(BorderFactory.createTitledBorder("Descripción / Diagnóstico"));
        add(new JScrollPane(txtDescripcion), BorderLayout.CENTER);

        // ---------- Panel inferior ----------
        JPanel panelAbajo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnGuardar = new JButton("Guardar Atención");
        panelAbajo.add(btnGuardar);
        add(panelAbajo, BorderLayout.SOUTH);

        // ---------- Listeners ----------
        btnBuscar.addActionListener(e -> buscarGato());
        btnGuardar.addActionListener(e -> guardarHistorial());

        // Carga inicial de listas
        cargarCombos();
    }

    private void cargarCombos() {
        List<Tratamiento> tratamientos = controlador.obtenerTratamientos();
        cbTratamiento.removeAllItems();
        for (Tratamiento t : tratamientos) {
            cbTratamiento.addItem(t.getNombreTratamiento());
        }

        List<Estudios> estudios = controlador.obtenerEstudios();
        cbEstudio.removeAllItems();
        for (Estudios e : estudios) {
            cbEstudio.addItem(e.getNombreEstudio());
        }
    }

    private void buscarGato() {
        String codigoQR = txtCodigoQR.getText().trim();
        if (codigoQR.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresá un código QR.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Gato gato = controlador.buscarGatoPorCodigo(codigoQR);
        if (gato != null) {
            JOptionPane.showMessageDialog(this,
                    "Gato encontrado:\nNombre: " + gato.getNombre() + "\nEstado: " + gato.getEstado(),
                    "Gato verificado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se encontró ningún gato con ese código.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarHistorial() {
        String codigoQR = txtCodigoQR.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String archivo = txtArchivoAdjunto.getText().trim();

        if (codigoQR.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completá los campos obligatorios.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean ok = controlador.guardarHistorial(
                codigoQR,
                descripcion,
                veterinarioActual,
                (String) cbTratamiento.getSelectedItem(),
                (String) cbEstudio.getSelectedItem(),
                archivo
        );

        if (ok) {
            JOptionPane.showMessageDialog(this, "Historial registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtDescripcion.setText("");
            txtArchivoAdjunto.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el historial.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
