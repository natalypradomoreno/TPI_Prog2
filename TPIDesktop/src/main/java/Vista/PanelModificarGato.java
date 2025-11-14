package Vista;


import Controlador.ControladorGato;
import modelo.Gato;

import javax.swing.*;
import java.awt.*;



/**
 * panel para buscar un gato por codigo QR y modificar su estado y situacion
 * puede ser usado tanto por veterinarios como voluntarios
 */
public class PanelModificarGato extends JPanel {

    private final JTextField txtCodigoQR;
    private final JButton btnBuscar;
    private final JComboBox<String> cbEstado;
    private final JComboBox<String> cbSituacion;
    private final JLabel lblEstadoActual;
    private final JLabel lblSituacionActual;
    private final JButton btnGuardar;

    private final ControladorGato controlador;
    private Gato gatoEncontrado;

    public PanelModificarGato() {
        controlador = new ControladorGato();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Modificar Estado y Situación de un Gato"));

        JPanel panelArriba = new JPanel(new GridLayout(5, 2, 5, 5));

        panelArriba.add(new JLabel("Código QR del gato:"));
        JPanel panelQR = new JPanel(new BorderLayout());
        txtCodigoQR = new JTextField();
        btnBuscar = new JButton("Buscar");
        panelQR.add(txtCodigoQR, BorderLayout.CENTER);
        panelQR.add(btnBuscar, BorderLayout.EAST);
        panelArriba.add(panelQR);

        panelArriba.add(new JLabel("Estado actual:"));
        lblEstadoActual = new JLabel("-");
        panelArriba.add(lblEstadoActual);

        panelArriba.add(new JLabel("Situación actual:"));
        lblSituacionActual = new JLabel("-");
        panelArriba.add(lblSituacionActual);

        panelArriba.add(new JLabel("Nuevo Estado:"));
        cbEstado = new JComboBox<>(new String[]{
            "Enfermo", "Sano", "En tratamiento", "Esterilizado"
        });
        panelArriba.add(cbEstado);

        panelArriba.add(new JLabel("Nueva Situación:"));
        cbSituacion = new JComboBox<>(new String[]{
            "En adopción (0)", "En tránsito (1)", "Adoptado (2)"
        });
        panelArriba.add(cbSituacion);

        add(panelArriba, BorderLayout.CENTER);

        btnGuardar = new JButton("Guardar Cambios");
        add(btnGuardar, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarGato());
        btnGuardar.addActionListener(e -> guardarCambios());
    }

    private void buscarGato() {
        String codigoQR = txtCodigoQR.getText().trim();
        if (codigoQR.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresá un código QR.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        gatoEncontrado = controlador.buscarGatoPorCodigo(codigoQR);

        if (gatoEncontrado != null) {
            lblEstadoActual.setText(gatoEncontrado.getEstado());
            String situacionTexto = switch (gatoEncontrado.getEnTransito()) {
                case 1 -> "En tránsito";
                case 2 -> "Adoptado";
                default -> "En adopción";
            };
            lblSituacionActual.setText(situacionTexto);

            JOptionPane.showMessageDialog(this,
                    "Gato encontrado:\nNombre: " + gatoEncontrado.getNombre(),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            lblEstadoActual.setText("-");
            lblSituacionActual.setText("-");
            JOptionPane.showMessageDialog(this,
                    "No se encontró ningún gato con ese código.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCambios() {
        if (gatoEncontrado == null) {
            JOptionPane.showMessageDialog(this, "Primero buscá un gato válido.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nuevoEstado = (String) cbEstado.getSelectedItem();
        int nuevaSituacion = cbSituacion.getSelectedIndex(); // 0, 1 o 2

        gatoEncontrado.setEstado(nuevoEstado);
        gatoEncontrado.setSituacion(nuevaSituacion);

        try {
            controlador.editarGato(gatoEncontrado);
            JOptionPane.showMessageDialog(this, "Cambios guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            lblEstadoActual.setText(nuevoEstado);
            lblSituacionActual.setText(
                    nuevaSituacion == 0 ? "En adopción" :
                    nuevaSituacion == 1 ? "En tránsito" : "Adoptado"
            );

            txtCodigoQR.setText("");
            gatoEncontrado = null;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar cambios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

