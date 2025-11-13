/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author natal
 */
import Controlador.ControladorGato;
import Controlador.ControladorPostulaciones;
import modelo.FamiliaUsuario;
import modelo.Gato;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelGatosDisponibles extends JPanel {

    private final JComboBox<Gato> cbGatos;
    private final JButton btnPostularse;
    private final JTextArea txtDetalle;

    private final ControladorGato controladorGato;
    private final ControladorPostulaciones controladorPost;
    private final FamiliaUsuario familia;

    public PanelGatosDisponibles(FamiliaUsuario fam) {
        this.familia = fam;
        this.controladorGato = new ControladorGato();
        this.controladorPost = new ControladorPostulaciones();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Gatos disponibles para postularse"));

        // ===================================================
        // PANEL SUPERIOR - COMBO
        // ===================================================
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Seleccionar gato: "));

        cbGatos = new JComboBox<>();
        top.add(cbGatos);
        add(top, BorderLayout.NORTH);

        // ===================================================
        // PANEL CENTRAL - DETALLE DEL GATO
        // (CREAR txtDetalle ANTES DE cargarGatosDisponibles() !!!!)
        // ===================================================
        txtDetalle = new JTextArea(10, 40);
        txtDetalle.setEditable(false);
        txtDetalle.setBorder(BorderFactory.createTitledBorder("Detalles del gato"));
        add(new JScrollPane(txtDetalle), BorderLayout.CENTER);

        // ===================================================
        // AHORA SÍ CARGAR GATOS
        // ===================================================
        cargarGatosDisponibles();

        cbGatos.addActionListener(e -> mostrarDetalle());

        // ===================================================
        // PANEL INFERIOR – BOTÓN
        // ===================================================
        btnPostularse = new JButton("Postularse");
        add(btnPostularse, BorderLayout.SOUTH);

        btnPostularse.addActionListener(e -> postular());
    }

    // ============================================================
    // CARGAR GATOS (solo situacion 0 o 1)
    // ============================================================
    private void cargarGatosDisponibles() {
        cbGatos.removeAllItems();

        List<Gato> lista = controladorGato.listarGatos();
        for (Gato g : lista) {
            if (g.getSituacion() == 0 || g.getSituacion() == 1) {
                cbGatos.addItem(g);
            }
        }

        mostrarDetalle();
    }

    // ============================================================
    // MOSTRAR DETALLE SIN "estado"
    // ============================================================
    private void mostrarDetalle() {
        Gato g = (Gato) cbGatos.getSelectedItem();

        if (g == null) {
            txtDetalle.setText("No hay gatos disponibles.");
            return;
        }

        String situacionStr = (g.getSituacion() == 0 ? "En adopción" : "En tránsito");

        txtDetalle.setText(
                "Nombre: " + g.getNombre() + "\n" +
                "Raza: " + g.getRaza() + "\n" +
                "Color: " + g.getColor() + "\n" +
                "Características: " + g.getCaracteristicas() + "\n" +
                "Situación: " + situacionStr
        );
    }

    // ============================================================
    // POSTULARSE (reglas finales aplicadas)
    // ============================================================
    private void postular() {

        Gato g = (Gato) cbGatos.getSelectedItem();

        if (g == null) {
            JOptionPane.showMessageDialog(this, "No seleccionaste ningún gato.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tipoHogar = familia.getHogar().getTipo(); // "1" o "2"
        int situacionActual = g.getSituacion();

        // REGLAS
        if (tipoHogar.equals("1") && situacionActual == 1) {
            JOptionPane.showMessageDialog(this,
                    "Este gato ya está en tránsito.\nNo puede postularse.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int tipoPost = Integer.parseInt(tipoHogar);

        boolean ok = controladorPost.crearPostulacion(
                familia.getHogar(),
                g,
                familia,
                tipoPost
        );

        if (ok) {
            JOptionPane.showMessageDialog(this,
                    "Postulación enviada con éxito.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Ya existe una postulación pendiente para este gato.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
