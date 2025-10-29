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
 Ventana del rol Familia Adoptante con panel de visitas.
 */
public class VentanaFamilia extends JFrame {

    private JButton btnVisitas;
    private JButton btnSalir;
    private JPanel panelCentral;

    public VentanaFamilia() {
        setTitle("Panel Familia Adoptante");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel menu = new JPanel(new GridLayout(2, 1, 5, 5));
        btnVisitas = new JButton("Ver Visitas");
        btnSalir = new JButton("Cerrar Sesión");

        menu.add(btnVisitas);
        menu.add(btnSalir);
        add(menu, BorderLayout.WEST);

        panelCentral = new JPanel(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);

        btnVisitas.addActionListener(e -> mostrarPanel(new PanelVisita()));
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
}
