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
import modelo.Voluntario;

/*
 Ventana del rol Voluntario con cambio entre paneles.
 */
public class VentanaVoluntario extends JFrame {

    private JButton btnRegistrarGato;
    private JButton btnRegistrarTarea;
    private JButton btnModificarGato;
    private JButton btnSolicitudes; 
    private JButton btnVisita;
    private JButton btnSalir;
    private JPanel panelCentral;

    public VentanaVoluntario(Voluntario vol) {
        setTitle("Panel Voluntario");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ----- PANEL LATERAL DE MENÚ -----
        JPanel menu = new JPanel(new GridLayout(5, 1, 5, 5));
        menu.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        btnRegistrarGato = new JButton("Registrar Gato");
        btnRegistrarTarea = new JButton("Registrar Tarea");
        btnModificarGato = new JButton("Modificar Estado/Situación de Gato");
        btnSolicitudes = new JButton("Solicitudes de adopción");
        btnVisita = new JButton("Registrar Visita");
        btnSalir = new JButton("Cerrar Sesión");

        // Agregamos TODOS los botones al menú
        menu.add(btnRegistrarGato);
        menu.add(btnRegistrarTarea);
        menu.add(btnModificarGato);
        menu.add(btnSolicitudes);
        menu.add(btnVisita);
        menu.add(btnSalir);

        add(menu, BorderLayout.WEST);

        // ----- PANEL CENTRAL -----
        panelCentral = new JPanel(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);

        // ----- ACCIONES -----
        btnRegistrarGato.addActionListener(e -> mostrarPanel(new PanelRegistrarGato()));
        btnRegistrarTarea.addActionListener(e -> mostrarPanel(new PanelRegistrarTarea()));
        btnModificarGato.addActionListener(e -> mostrarPanel(new PanelModificarGato()));
        btnSolicitudes.addActionListener(e -> mostrarPanel(new PanelSolicitudesAdopcion()));
        btnVisita.addActionListener(e -> mostrarPanel(new PanelVisita()));
        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaLogin().setVisible(true);
        });
    }

    // Método reutilizable para cambiar paneles
    private void mostrarPanel(JPanel nuevo) {
        panelCentral.removeAll();
        panelCentral.add(nuevo, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
        this.pack();
    }

  
    
}
