/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

/**
 *
 * @author natal
 */

import modelo.Veterinario;
import javax.swing.*;
import java.awt.*;

/*
 Ventana del rol Veterinario con sus paneles.
 */
public class VentanaVeterinario extends JFrame {

    private final JButton btnHistorial;
    private final JButton btnTratamiento;
    private final JButton btnEmitirCertificado;
    private JButton btnModificarGato;
    private final JButton btnSalir;
    private final JPanel panelCentral;

    private Veterinario veterinarioLogueado;

    public VentanaVeterinario(Veterinario vet) {
        this.veterinarioLogueado = vet;

        setTitle("Panel del Veterinario");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel lateral con menú
        JPanel menu = new JPanel(new GridLayout(4, 1, 5, 5));
        btnHistorial = new JButton("Historial Médico");
        btnTratamiento = new JButton("Tratamiento");
        btnEmitirCertificado = new JButton("Emitir Certificado");
        btnSalir = new JButton("Cerrar Sesión");
    btnModificarGato = new JButton("Modificar Estado/Situación de Gato");

    menu.add(btnHistorial);
    menu.add(btnTratamiento);
    menu.add(btnEmitirCertificado);
    menu.add(btnModificarGato);
    menu.add(btnSalir);



        add(menu, BorderLayout.WEST);

        // Panel central donde se muestran los subpaneles
        panelCentral = new JPanel(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);

        // Acciones de botones
        btnHistorial.addActionListener(e -> mostrarPanel(new PanelHistorialMedico(veterinarioLogueado)));

        btnTratamiento.addActionListener(e -> mostrarPanel(new PanelTratamiento()));
        btnModificarGato.addActionListener(e -> mostrarPanel(new PanelModificarGato()));

        btnEmitirCertificado.addActionListener(e -> {
            panelCentral.removeAll();
            panelCentral.add(new PanelEmitirCertificado(veterinarioLogueado), BorderLayout.CENTER);
            panelCentral.revalidate();
            panelCentral.repaint();
        });

        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaLogin().setVisible(true);
        });
    }

    //VentanaVeterinario() {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}

    private void mostrarPanel(JPanel nuevo) {
        panelCentral.removeAll();
        panelCentral.add(nuevo, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    
}
