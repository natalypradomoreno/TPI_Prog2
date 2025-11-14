package Vista;

import Controlador.ControladorPostulaciones;
import Controlador.ControladorGato;
import modelo.Postulacion;
import modelo.Gato;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelSolicitudesAdopcion extends JPanel {
    

    private final ControladorPostulaciones controladorPost;
    private final ControladorGato controladorGato;

    private final DefaultListModel<Postulacion> modeloLista;
    private final JList<Postulacion> listaPostulaciones;

    private JLabel lblGato;
    private JLabel lblSituacion;
    private JLabel lblFamilia;
    private JLabel lblTelefono;
    private JLabel lblTipoHogar;

    private JButton btnAceptar;
    private JButton btnCancelar;

 public PanelSolicitudesAdopcion() {

   
        controladorPost = new ControladorPostulaciones();
        controladorGato = new ControladorGato();

        // panel principal flexible
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Solicitudes de adopción pendientes"));

        modeloLista = new DefaultListModel<>();
        listaPostulaciones = new JList<>(modeloLista);
        listaPostulaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollLista = new JScrollPane(listaPostulaciones);
        scrollLista.setPreferredSize(new Dimension(300, 400));
        add(scrollLista, BorderLayout.WEST);

        JPanel panelDet = new JPanel(new GridLayout(8, 1, 8, 8));
        panelDet.setBorder(BorderFactory.createTitledBorder("Detalle de la solicitud"));

        lblGato = new JLabel("Gato:");
        lblSituacion = new JLabel("Situación:");
        lblFamilia = new JLabel("Familia:");
        lblTelefono = new JLabel("Teléfono:");
        lblTipoHogar = new JLabel("Tipo de hogar:");

        panelDet.add(lblGato);
        panelDet.add(lblSituacion);
        panelDet.add(lblFamilia);
        panelDet.add(lblTelefono);
        panelDet.add(lblTipoHogar);

        add(panelDet, BorderLayout.CENTER);

    JPanel panelBotones = new JPanel(new FlowLayout());
    btnAceptar = new JButton("Aceptar postulación");
    btnCancelar = new JButton("Actualizar");

    panelBotones.add(btnAceptar);
    panelBotones.add(btnCancelar);
    add(panelBotones, BorderLayout.SOUTH);

    listaPostulaciones.addListSelectionListener(e -> mostrarDetalle());
    btnAceptar.addActionListener(e -> aceptarPostulacion());
    btnCancelar.addActionListener(e -> cargarPendientes());

    cargarPendientes();
}


    // cargar lista de postulaciones pendientes
    private void cargarPendientes() {
        modeloLista.clear();
        List<Postulacion> lista = controladorPost.listarPendientes();

        for (Postulacion p : lista) {
            modeloLista.addElement(p);
        }

        limpiarDetalle();
    }

    private void limpiarDetalle() {
        lblGato.setText("Gato:");
        lblSituacion.setText("Situación:");
        lblFamilia.setText("Familia:");
        lblTelefono.setText("Teléfono:");
        lblTipoHogar.setText("Tipo de hogar:");
    }

    private void mostrarDetalle() {
        Postulacion p = listaPostulaciones.getSelectedValue();

        if (p == null) return;

        Gato g = p.getGato();

        lblGato.setText("Gato: " + g.getNombre());

        String situacion = "En adopción";
        if (g.getSituacion() == 1) situacion = "En tránsito";

        lblSituacion.setText("Situación: " + situacion);

        lblFamilia.setText("Familia: " + p.getUsuario().getUsername());
        lblTelefono.setText("Teléfono: " + p.getUsuario().getTelefono());

        String tipo = p.getHogar().getTipo().equals("1") ? "Tránsito" : "Adoptivo";
        lblTipoHogar.setText("Tipo de hogar: " + tipo);
    }

    private void aceptarPostulacion() {

        Postulacion p = listaPostulaciones.getSelectedValue();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una postulación.");
            return;
        }

        Gato g = p.getGato();
        String tipoHogar = p.getHogar().getTipo();  
        int situacionActual = g.getSituacion();      

        if (tipoHogar.equals("1")) {

            if (situacionActual == 1) {
                JOptionPane.showMessageDialog(this,
                        "El gato ya está en tránsito.\nNo se puede aceptar esta postulación.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            g.setSituacion(1); 
        }

        if (tipoHogar.equals("2")) {
            g.setSituacion(2); 
        }

        controladorGato.editarGato(g);

        controladorPost.aceptarPostulacion(p.getIdPostulacion());

        JOptionPane.showMessageDialog(this,
                "Postulación aceptada.\n" +
                "Contacto de la familia: " + p.getUsuario().getTelefono(),
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        cargarPendientes(); 
    }
}

