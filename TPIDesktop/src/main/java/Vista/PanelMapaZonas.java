package Vista;

import Controlador.ControladorZonas;
import modelo.Zona;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PanelMapaZonas extends JPanel {

    private final JXMapViewer mapViewer;
    private final ControladorZonas controladorZonas;

    public PanelMapaZonas() {
        controladorZonas = new ControladorZonas();
        setLayout(new BorderLayout());

        // ---- Título ----
        JLabel lblTitulo = new JLabel("Mapa geolocalizado de zonas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // ---- Configuración base del mapa ----
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

        // Centro inicial (Posadas aprox) y zoom
        mapViewer.setZoom(4);
        mapViewer.setAddressLocation(new GeoPosition(-27.36, -55.90));

        add(new JScrollPane(mapViewer), BorderLayout.CENTER);

        // ---- Marcadores ----
        cargarZonasEnMapa();
    }

    private void cargarZonasEnMapa() {
        try {
            List<Zona> zonas = controladorZonas.listarZonas();
            Set<Waypoint> waypoints = new HashSet<>();

            for (Zona z : zonas) {
                String gps = z.getUbicacionGPS();
                if (gps == null || gps.trim().isEmpty()) continue;

                String[] partes = gps.split(",");
                if (partes.length != 2) continue;

                try {
                    double lat = Double.parseDouble(partes[0].trim());
                    double lon = Double.parseDouble(partes[1].trim());

                    GeoPosition pos = new GeoPosition(lat, lon);
                    waypoints.add(new DefaultWaypoint(pos));

                } catch (NumberFormatException ex) {
                    System.err.println("Coordenadas inválidas en zona " 
                            + z.getNombreZona() + ": " + gps);
                }
            }

            WaypointPainter<Waypoint> painter = new WaypointPainter<>();
            painter.setWaypoints(waypoints);
            mapViewer.setOverlayPainter(painter);

            // Centrar en la primera zona válida
            waypoints.stream().findFirst().ifPresent(wp -> {
                GeoPosition p = ((DefaultWaypoint) wp).getPosition();
                mapViewer.setAddressLocation(p);
                mapViewer.setZoom(6);
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al cargar el mapa de zonas: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}