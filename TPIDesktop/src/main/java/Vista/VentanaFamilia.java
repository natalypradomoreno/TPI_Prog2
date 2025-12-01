package Vista;


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

        panelCentral = new JPanel(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);

        if (familia.getHogar() == null) {
            mostrarPanel(new PanelSeleccionHogar(familia));
            configurarMenuBasico();  
            return;        
        }

        configurarMenuCompleto();
    }


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


    private void configurarMenuCompleto() {

        JPanel menu = new JPanel(new GridLayout(3, 1, 5, 5));

        btnGatosDisponibles = new JButton("Gatos disponibles");
        btnSalir = new JButton("Cerrar Sesión");


        menu.add(btnGatosDisponibles);
        menu.add(btnSalir);
        add(menu, BorderLayout.WEST);

        btnGatosDisponibles.addActionListener(e -> 
            mostrarPanel(new PanelGatosDisponibles(familia))
        );

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

