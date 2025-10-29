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
 Ventana del rol Voluntario con cambio entre paneles.
 */
public class VentanaVoluntario extends JFrame {

    private JButton btnRegistrarGato;
    private JButton btnRegistrarTarea;
    private JButton btnVisita;
    private JButton btnSalir;
    private JPanel panelCentral;

    public VentanaVoluntario() {
        setTitle("Panel Voluntario");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel menu = new JPanel(new GridLayout(4, 1, 5, 5));
        btnRegistrarGato = new JButton("Registrar Gato");
        btnRegistrarTarea = new JButton("Registrar Tarea");
        btnVisita = new JButton("Registrar Visita");
        btnSalir = new JButton("Cerrar Sesión");

        menu.add(btnRegistrarGato);
        menu.add(btnRegistrarTarea);
        menu.add(btnVisita);
        menu.add(btnSalir);

        add(menu, BorderLayout.WEST);

        panelCentral = new JPanel(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);

        // acciones
        btnRegistrarGato.addActionListener(e -> mostrarPanel(new PanelRegistrarGato()));
        btnRegistrarTarea.addActionListener(e -> mostrarPanel(new PanelRegistrarTarea()));
        btnVisita.addActionListener(e -> mostrarPanel(new PanelVisita()));
        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaLogin().setVisible(true);
        });
    }

    private void mostrarPanel(JPanel nuevo) {
        panelCentral.removeAll();
        panelCentral.add(nuevo, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaVoluntario().setVisible(true));
    }
}

