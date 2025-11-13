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
import modelo.FamiliaUsuario;

public class VentanaFamilia extends JFrame {

    private JButton btnVisitas;
    private JButton btnGatosDisponibles; 
    private JButton btnSalir;
    private final JPanel panelCentral;

    private final FamiliaUsuario familia;

    public VentanaFamilia(FamiliaUsuario fam) {

        this.familia = fam;

        setTitle("Panel Familia");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // =====================================================
        // 1. VERIFICAR SI LA FAMILIA TIENE HOGAR ASIGNADO
        // =====================================================
        panelCentral = new JPanel(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);

        if (familia.getHogar() == null) {
            mostrarPanel(new PanelSeleccionHogar(familia));
            configurarMenuBasico();  // menú sin opciones de postulaciones
            return;  // se queda en este panel hasta vincular hogar
        }

        // =====================================================
        // 2. SI TIENE HOGAR → CARGA NORMAL DEL PANEL
        // =====================================================
        configurarMenuCompleto();
    }


    // ---------------------------------------------------------
    // MENÚ cuando NO tiene hogar asignado
    // ---------------------------------------------------------
    private void configurarMenuBasico() {
        JPanel menu = new JPanel(new GridLayout(1, 1, 5, 5));

        btnSalir = new JButton("Cerrar Sesión");
        menu.add(btnSalir);

        add(menu, BorderLayout.WEST);

        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaLogin().setVisible(true);
        });
    }


    // ---------------------------------------------------------
    // MENÚ COMPLETO cuando YA tiene hogar asignado
    // ---------------------------------------------------------
    private void configurarMenuCompleto() {

        JPanel menu = new JPanel(new GridLayout(3, 1, 5, 5));

        btnVisitas = new JButton("Ver Visitas");
        btnGatosDisponibles = new JButton("Gatos disponibles");
        btnSalir = new JButton("Cerrar Sesión");

        menu.add(btnVisitas);
        menu.add(btnGatosDisponibles);
        menu.add(btnSalir);

        add(menu, BorderLayout.WEST);

        // BOTONES
        btnVisitas.addActionListener(e -> mostrarPanel(new PanelVisita()));

        btnGatosDisponibles.addActionListener(e -> 
            mostrarPanel(new PanelGatosDisponibles(familia))
        );

        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaLogin().setVisible(true);
        });


    }


    // ---------------------------------------------------------
    // Método universal para cambiar paneles
    // ---------------------------------------------------------
    private void mostrarPanel(JPanel nuevo) {
        panelCentral.removeAll();
        panelCentral.add(nuevo, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}

