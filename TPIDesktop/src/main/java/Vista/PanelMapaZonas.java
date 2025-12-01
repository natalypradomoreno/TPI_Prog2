package Vista;

import Controlador.ControladorZonas;
import modelo.Zona;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PanelMapaZonas extends JPanel {

    private final JXMapViewer mapViewer;
    private final ControladorZonas controladorZonas;
    private JComboBox<Zona> comboZonas;

    public PanelMapaZonas() {
        controladorZonas = new ControladorZonas();
        setLayout(new BorderLayout());

        // ===== PANEL SUPERIOR (Título + Combo) =====
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel lblTitulo = new JLabel("Mapa geolocalizado de zonas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelSeleccion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSeleccion.add(new JLabel("Seleccionar zona:"));

        comboZonas = new JComboBox<>();
        panelSeleccion.add(comboZonas);

        panelSuperior.add(panelSeleccion, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);

        // ===== CONFIGURACIÓN BASE DEL MAPA =====
        mapViewer = new JXMapViewer();

        // Usamos OSM, pero forzando HTTPS en la URL final de los tiles
        OSMTileFactoryInfo info = new OSMTileFactoryInfo() {
            @Override
            public String getTileUrl(int x, int y, int zoom) {
                String url = super.getTileUrl(x, y, zoom);
                if (url.startsWith("http://")) {
                    url = "https://" + url.substring("http://".length());
                }
                return url;
            }
        };

        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        tileFactory.setThreadPoolSize(8);
        mapViewer.setTileFactory(tileFactory);

        // Centro inicial (ej: Posadas)
        mapViewer.setZoom(4);
        mapViewer.setAddressLocation(new GeoPosition(-27.36, -55.90));

        // Habilitar movimiento y zoom con el mouse
        mapViewer.setPanEnabled(true);
        MouseInputListener pan = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(pan);
        mapViewer.addMouseMotionListener(pan);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));

        // IMPORTANTE: agregar el JXMapViewer directo (sin JScrollPane),
        // así no interfiere con el drag/zoom del mapa.
        add(mapViewer, BorderLayout.CENTER);

        // Cargar zonas (marcadores + combo)
        cargarZonasEnMapa();

        // Cuando el usuario elige una zona en el combo
        comboZonas.addActionListener(e -> centrarEnZonaSeleccionada());
    }

    private void cargarZonasEnMapa() {
        try {
            List<Zona> zonas = controladorZonas.listarZonas();

            Set<Waypoint> waypoints = new HashSet<>();
            DefaultComboBoxModel<Zona> modeloCombo = new DefaultComboBoxModel<>();

            GeoPosition primeraPosValida = null;

            for (Zona z : zonas) {
                modeloCombo.addElement(z); // lo agregamos al combo siempre

                String gps = z.getUbicacionGPS();
                if (gps == null || gps.trim().isEmpty()) {
                    continue;
                }

                String[] partes = gps.split(",");
                if (partes.length != 2) continue;

                try {
                    double lat = Double.parseDouble(partes[0].trim());
                    double lon = Double.parseDouble(partes[1].trim());

                    GeoPosition pos = new GeoPosition(lat, lon);
                    waypoints.add(new DefaultWaypoint(pos));

                    if (primeraPosValida == null) {
                        primeraPosValida = pos;
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("Coordenadas inválidas en zona "
                            + z.getNombreZona() + ": " + gps);
                }
            }

            comboZonas.setModel(modeloCombo);

            // Pintar todos los waypoints
            WaypointPainter<Waypoint> painter = new WaypointPainter<>();
            painter.setWaypoints(waypoints);
            mapViewer.setOverlayPainter(painter);

            // Centrar en la primera zona válida
            if (primeraPosValida != null) {
                mapViewer.setAddressLocation(primeraPosValida);
                mapViewer.setZoom(6);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar el mapa de zonas: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void centrarEnZonaSeleccionada() {
        Zona z = (Zona) comboZonas.getSelectedItem();
        if (z == null) return;

        String gps = z.getUbicacionGPS();
        if (gps == null || gps.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "La zona seleccionada no tiene coordenadas GPS cargadas.",
                    "Sin coordenadas", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] partes = gps.split(",");
        if (partes.length != 2) {
            JOptionPane.showMessageDialog(this,
                    "Coordenadas inválidas para la zona: " + gps,
                    "Error de coordenadas", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double lat = Double.parseDouble(partes[0].trim());
            double lon = Double.parseDouble(partes[1].trim());

            GeoPosition pos = new GeoPosition(lat, lon);
            mapViewer.setAddressLocation(pos);
            mapViewer.setZoom(6); // ajustá el nivel de zoom a gusto

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "No se pudieron interpretar las coordenadas: " + gps,
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}
