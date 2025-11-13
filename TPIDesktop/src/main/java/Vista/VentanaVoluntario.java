package Vista;

import javax.swing.*;
import java.awt.*;
import modelo.Voluntario;

/*
 ventana principal del voluntario.
 basicamente es un menu a la izquierda con botones, y un panel central
 donde se van cargando los formularios que el voluntario va usando.
*/
public class VentanaVoluntario extends JFrame {

    private JButton btnRegistrarGato;
    private JButton btnRegistrarTarea;
    private JButton btnModificarGato;
    private JButton btnVisita;
    private JButton btnCambiarEstadoVisita; // boton nuevo para el modulo de visitas
    private JButton btnSalir;

    private JPanel panelCentral;

    public VentanaVoluntario(Voluntario vol) {
        setTitle("Panel Voluntario");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        /*
         el menu lateral es un grid vertical con todos los botones.
         cada boton abre un panel distinto en el panelCentral.
        */
        JPanel menu = new JPanel(new GridLayout(6, 1, 5, 5));
        menu.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        btnRegistrarGato = new JButton("Registrar Gato");
        btnRegistrarTarea = new JButton("Registrar Tarea");
        btnModificarGato = new JButton("Modificar Estado/Situacion de Gato");
        btnVisita = new JButton("Registrar Visita");
        btnCambiarEstadoVisita = new JButton("Cambiar Estado de Visita");
        btnSalir = new JButton("Cerrar Sesion");

        // agrego los botones en orden
        menu.add(btnRegistrarGato);
        menu.add(btnRegistrarTarea);
        menu.add(btnModificarGato);
        menu.add(btnVisita);
        menu.add(btnCambiarEstadoVisita); // nuevo boton
        menu.add(btnSalir);

        add(menu, BorderLayout.WEST);

        /*
         panelCentral es donde se van mostrando los distintos paneles
         segun el boton que toque el voluntario.
        */
        panelCentral = new JPanel(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);

        /*
         listeners de los botones: cada uno carga un panel distinto.
         no hay logica aca, solo cambiar lo que se ve.
        */
        btnRegistrarGato.addActionListener(e -> mostrarPanel(new PanelRegistrarGato()));
        btnRegistrarTarea.addActionListener(e -> mostrarPanel(new PanelRegistrarTarea()));
        btnModificarGato.addActionListener(e -> mostrarPanel(new PanelModificarGato()));
        btnVisita.addActionListener(e -> mostrarPanel(new PanelVisita()));

        // panel nuevo para actualizar estado de visitas pendientes
        btnCambiarEstadoVisita.addActionListener(e -> mostrarPanel(new PanelCambiarEstadoVisita()));

        // boton de salir que vuelve al login
        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaLogin().setVisible(true);
        });
    }

    /*
     este metodo es clave porque permite cambiar lo que se muestra
     en el centro de la ventana. todos los botones usan esto.
    */
    private void mostrarPanel(JPanel nuevo) {
        panelCentral.removeAll();
        panelCentral.add(nuevo, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}
