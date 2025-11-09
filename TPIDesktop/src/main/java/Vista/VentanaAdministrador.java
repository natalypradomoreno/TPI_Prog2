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

/**
 * Ventana principal del rol Administrador.
 * Permite acceder al panel de reportes, gestionar usuarios y cerrar sesión.
 */
public class VentanaAdministrador extends JFrame {

    private final JButton btnReportes;
    private final JButton btnUsuarios;
    private final JButton btnSalir;
    private final JPanel panelCentral;

    public VentanaAdministrador() {
        setTitle("Panel del Administrador");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ---------- Panel lateral de menú ----------
        JPanel panelMenu = new JPanel(new GridLayout(3, 1, 5, 5));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        btnReportes = new JButton("Ver Reportes");
        btnUsuarios = new JButton("Gestionar Usuarios");
        btnSalir = new JButton("Cerrar Sesión");

        panelMenu.add(btnReportes);
        panelMenu.add(btnUsuarios);
        panelMenu.add(btnSalir);
        add(panelMenu, BorderLayout.WEST);

        // ---------- Panel central dinámico ----------
        panelCentral = new JPanel(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);

        // ---------- Acciones ----------
        btnReportes.addActionListener(e -> mostrarPanel(new PanelReportes()));
        btnUsuarios.addActionListener(e -> mostrarPanel(new PanelGestionUsuarios()));

        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaLogin().setVisible(true);
        });
    }

    /**
     * Muestra dinámicamente el panel indicado dentro del área central.
     */
    private void mostrarPanel(JPanel nuevo) {
        panelCentral.removeAll();
        panelCentral.add(nuevo, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}
