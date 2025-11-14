package Vista;

import modelo.Veterinario;
import javax.swing.*;
import java.awt.*;

/*
 ventana del rol veterinario con sus paneles
 */
public class VentanaVeterinario extends JFrame {

    private final JButton btnHistorial;
    private final JButton btnTratamiento;
    private final JButton btnEstudio;
    private final JButton btnEmitirCertificado;
    private final JButton btnModificarGato;
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

        JPanel menu = new JPanel(new GridLayout(4, 1, 5, 5));
        btnHistorial = new JButton("Historial Médico");
        btnTratamiento = new JButton("Nuevo Tratamiento");
         btnEstudio = new JButton("Nuevo Estudio");
        btnEmitirCertificado = new JButton("Emitir Certificado");
        btnSalir = new JButton("Cerrar Sesión");
    btnModificarGato = new JButton("Modificar Estado/Situación de Gato");

    menu.add(btnHistorial);
    menu.add(btnTratamiento);
     menu.add(btnEstudio);
    menu.add(btnEmitirCertificado);
    menu.add(btnModificarGato);
    menu.add(btnSalir);



        add(menu, BorderLayout.WEST);

        panelCentral = new JPanel(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);

        btnHistorial.addActionListener(e -> mostrarPanel(new PanelHistorialMedico(veterinarioLogueado)));
        btnTratamiento.addActionListener(e -> mostrarPanel(new PanelTratamiento()));
         btnEstudio.addActionListener(e -> mostrarPanel(new PanelEstudio()));
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


    private void mostrarPanel(JPanel nuevo) {
        panelCentral.removeAll();
        panelCentral.add(nuevo, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    
}
