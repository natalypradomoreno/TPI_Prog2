package Vista;


import Controlador.ControladorGato;
import Controlador.ControladorZonas;
import modelo.Gato;
import modelo.Zona;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * panel unificado de reportes donde el administrador puede elegir que reporte ver
 *  - registrar/Ver zonas
 *  - gatos por zona
 *  - gatos esterilizados
 *  - gatos adoptados
 */
public class PanelReportes extends JPanel {

    private final JComboBox<String> cbTipoReporte;
    private final JComboBox<String> cbZonas;
    private final JTextArea txtResultados;
    private final JPanel panelContenido;

    private final ControladorGato controladorGato;
    private final ControladorZonas controladorZona;

    public PanelReportes() {
        controladorGato = new ControladorGato();
        controladorZona = new ControladorZonas();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Reportes del Sistema"));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Seleccionar reporte:"));

        cbTipoReporte = new JComboBox<>(new String[]{
                "Registrar / Ver Zonas",
                "Gatos por Zona",
                "Gatos Esterilizados",
                "Gatos Adoptados"
        });
        panelSuperior.add(cbTipoReporte);

        // combo de zonas (solo visible en "gatos por zona")
        cbZonas = new JComboBox<>();
        panelSuperior.add(new JLabel("Zona:"));
        panelSuperior.add(cbZonas);

        add(panelSuperior, BorderLayout.NORTH);

        panelContenido = new JPanel(new BorderLayout());
        txtResultados = new JTextArea(15, 40);
        txtResultados.setEditable(false);
        txtResultados.setBorder(BorderFactory.createTitledBorder("Resultados del reporte"));
        panelContenido.add(new JScrollPane(txtResultados), BorderLayout.CENTER);

        add(panelContenido, BorderLayout.CENTER);

        cbTipoReporte.addActionListener(e -> mostrarPanelSeleccionado());
        cbZonas.addActionListener(e -> {
            if (cbTipoReporte.getSelectedItem().equals("Gatos por Zona")) {
                mostrarGatosPorZona();
            }
        });

        cargarZonas();
        mostrarPanelSeleccionado();
    }

    private void cargarZonas() {
        cbZonas.removeAllItems();
        List<Zona> zonas = controladorZona.listarZonas();
        for (Zona z : zonas) {
            cbZonas.addItem(z.getNombreZona());
        }
    }

    private void mostrarPanelSeleccionado() {
        String opcion = (String) cbTipoReporte.getSelectedItem();
        txtResultados.setText("");

        // Si se elige registrar zonas → mostramos otro panel
        if ("Registrar / Ver Zonas".equals(opcion)) {
            panelContenido.removeAll();
            panelContenido.add(new PanelRegistrarZona(), BorderLayout.CENTER);
        } else {
            panelContenido.removeAll();
            panelContenido.add(new JScrollPane(txtResultados), BorderLayout.CENTER);

            switch (opcion) {
                case "Gatos por Zona" -> mostrarGatosPorZona();
                case "Gatos Esterilizados" -> mostrarGatosPorEstado("Esterilizado");
                case "Gatos Adoptados" -> mostrarGatosAdoptados();
                default -> txtResultados.setText("Seleccioná un reporte válido.");
            }
        }

        panelContenido.revalidate();
        panelContenido.repaint();
    }



    private void mostrarGatosPorZona() {
        String nombreZona = (String) cbZonas.getSelectedItem();
        if (nombreZona == null) {
            txtResultados.setText("No hay zonas registradas.");
            return;
        }

        List<Gato> gatos = controladorGato.listarGatosPorZona(nombreZona);
        if (gatos.isEmpty()) {
            txtResultados.setText("No hay gatos registrados en la zona seleccionada.");
        } else {
            StringBuilder sb = new StringBuilder("Gatos en la zona '" + nombreZona + "':\n\n");
            for (Gato g : gatos) {
                sb.append("• ").append(g.getNombre())
                  .append(" | Estado: ").append(g.getEstado())
                  .append(" | Certificado: ").append(g.isCertificadoAptitud() ? "Sí" : "No")
                  .append("\n");
            }
            sb.append("\nTotal: ").append(gatos.size()).append(" gatos encontrados.");
            txtResultados.setText(sb.toString());
        }
    }

   
    private void mostrarGatosPorEstado(String estado) {

    // ejemplo de uso:
    // mostrarGatosPorEstado("desparasitado")

    List<Gato> gatos = controladorGato.listarGatosPorEstado(estado);

    if (gatos.isEmpty()) {
        txtResultados.setText("No hay gatos con estado '" + estado + "' registrados.");
        return;
    }

    StringBuilder sb = new StringBuilder("Gatos con estado '" + estado + "':\n\n");

    for (Gato g : gatos) {
        sb.append("• ").append(g.getNombre())
          .append(" | Zona: ")
          .append(g.getZona() != null ? g.getZona().getNombreZona() : "Sin zona")
          .append("\n");
    }

    sb.append("\nTotal: ").append(gatos.size()).append(" gatos encontrados.");
    txtResultados.setText(sb.toString());
}

    
    private void mostrarGatosAdoptados() {
    // 2 = adoptado
    List<Gato> gatos = controladorGato.listarGatosPorSituacion(2);

    if (gatos.isEmpty()) {
        txtResultados.setText("No hay gatos adoptados registrados.");
        return;
    }

    StringBuilder sb = new StringBuilder("Gatos Adoptados:\n\n");

    for (Gato g : gatos) {
        sb.append("• ").append(g.getNombre())
          .append(" | Zona: ")
          .append(g.getZona() != null ? g.getZona().getNombreZona() : "Sin zona")
          .append("\n");
    }

    sb.append("\nTotal: ").append(gatos.size());
    txtResultados.setText(sb.toString());
}

    
    
}

